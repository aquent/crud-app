package com.aquent.crudapp.data.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aquent.crudapp.data.dao.CompanyDao;
import com.aquent.crudapp.domain.Company;

/**
 * Spring JDBC implementation of {@link CompanyDao}.
 */
public class CompanyJdbcDao implements CompanyDao {

    private static final String SQL_LIST_COMPANIES = "SELECT * FROM company ORDER BY name, company_id";
    private static final String SQL_READ_COMPANY = "SELECT company.*, person.person_id FROM company LEFT OUTER JOIN person ON company.company_id = person.company_id WHERE company.company_id = :companyId";
    private static final String SQL_DELETE_COMPANY = "DELETE FROM company WHERE company_id = :companyId";
    private static final String SQL_UPDATE_COMPANY = "UPDATE company SET (name, website, phone_number, street_address, city, state, zip_code)"
                                                  + " = (:name, :website, :phoneNumber, :streetAddress, :city, :state, :zipCode)"
                                                  + " WHERE company_id = :companyId";
    private static final String SQL_CREATE_COMPANY = "INSERT INTO company (name, website, phone_number, street_address, city, state, zip_code)"
                                                  + " VALUES (:name, :website, :phoneNumber, :streetAddress, :city, :state, :zipCode)";
    private static final String SQL_CLEAR_PERSON_COMPANY = "UPDATE person SET (company_id) = (null) WHERE company_id = :companyId";
    private static final String SQL_UPDATE_PERSON_COMPANY = "UPDATE person SET (company_id) = (:companyId) WHERE person_id = :personId";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Company> listCompanies() {
        return namedParameterJdbcTemplate.getJdbcOperations().query(SQL_LIST_COMPANIES, new CompanyRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Company readCompany(Integer companyId) {
        return namedParameterJdbcTemplate.query(SQL_READ_COMPANY, Collections.singletonMap("companyId", companyId), new CompanyResultSetExtractor());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deleteCompany(Integer companyId) {
        namedParameterJdbcTemplate.update(SQL_CLEAR_PERSON_COMPANY, Collections.singletonMap("companyId", companyId));
        namedParameterJdbcTemplate.update(SQL_DELETE_COMPANY, Collections.singletonMap("companyId", companyId));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updateCompany(Company company) {
        namedParameterJdbcTemplate.update(SQL_UPDATE_COMPANY, new BeanPropertySqlParameterSource(company));
        namedParameterJdbcTemplate.update(SQL_CLEAR_PERSON_COMPANY, Collections.singletonMap("companyId", company.getCompanyId()));
        if (company.getPersonIds() != null && !company.getPersonIds().isEmpty()) {
	        for (Integer personId : company.getPersonIds()) {
	        	Map<String, Integer> parameterMap = new HashMap<>();
	        	parameterMap.put("companyId", company.getCompanyId());
	        	parameterMap.put("personId", personId);
				namedParameterJdbcTemplate.update(SQL_UPDATE_PERSON_COMPANY, parameterMap);
			}
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createCompany(Company company) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL_CREATE_COMPANY, new BeanPropertySqlParameterSource(company), keyHolder);
        if (company.getPersonIds() != null && !company.getPersonIds().isEmpty()) {
	        for (Integer personId : company.getPersonIds()) {
	        	Map<String, Integer> parameterMap = new HashMap<>();
	        	parameterMap.put("companyId", keyHolder.getKey().intValue());
	        	parameterMap.put("personId", personId);
				namedParameterJdbcTemplate.update(SQL_UPDATE_PERSON_COMPANY, parameterMap);
			}
        }
        return keyHolder.getKey().intValue();
    }

    /**
     * Row mapper for company records.
     */
    private static final class CompanyRowMapper implements RowMapper<Company> {

        @Override
        public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
            Company company = new Company();
            company.setCompanyId(rs.getInt("company_id"));
            company.setName(rs.getString("name"));
            company.setWebsite(rs.getString("website"));
            company.setPhoneNumber(rs.getString("phone_number"));
            company.setStreetAddress(rs.getString("street_address"));
            company.setCity(rs.getString("city"));
            company.setState(rs.getString("state"));
            company.setZipCode(rs.getString("zip_code"));
            return company;
        }
    }

    private static final class CompanyResultSetExtractor implements ResultSetExtractor<Company> {

		@Override
		public Company extractData(ResultSet rs) throws SQLException, DataAccessException {
			Company company = new Company();
			List<Integer> personIds = new ArrayList<>();
			while (rs.next()) {
				if (company.getCompanyId() != null && company.getCompanyId() != rs.getInt("company_id")) {
					throw new IncorrectResultSizeDataAccessException(1);
				}
				company.setCompanyId(rs.getInt("company_id"));
	            company.setName(rs.getString("name"));
	            company.setWebsite(rs.getString("website"));
	            company.setPhoneNumber(rs.getString("phone_number"));
	            company.setStreetAddress(rs.getString("street_address"));
	            company.setCity(rs.getString("city"));
	            company.setState(rs.getString("state"));
	            company.setZipCode(rs.getString("zip_code"));
	            if (rs.getString("person_id") != null) {
	            	personIds.add(rs.getInt("person_id"));
	            }
			}
			company.setPersonIds(personIds);
			return company;
		}

    }
}
