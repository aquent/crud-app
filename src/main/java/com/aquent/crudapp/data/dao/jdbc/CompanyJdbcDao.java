package com.aquent.crudapp.data.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

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

    private static final String SQL_LIST_PEOPLE = "SELECT * FROM company ORDER BY name, company_id";
    private static final String SQL_READ_PERSON = "SELECT * FROM company WHERE company_id = :companyId";
    private static final String SQL_DELETE_PERSON = "DELETE FROM company WHERE company_id = :companyId";
    private static final String SQL_UPDATE_PERSON = "UPDATE company SET (name, website, phone_number, street_address, city, state, zip_code)"
                                                  + " = (:name, :website, :phoneNumber, :streetAddress, :city, :state, :zipCode)"
                                                  + " WHERE company_id = :companyId";
    private static final String SQL_CREATE_PERSON = "INSERT INTO company (name, website, phone_number, street_address, city, state, zip_code)"
                                                  + " VALUES (:name, :website, :phoneNumber, :streetAddress, :city, :state, :zipCode)";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Company> listCompanies() {
        return namedParameterJdbcTemplate.getJdbcOperations().query(SQL_LIST_PEOPLE, new CompanyRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Company readCompany(Integer companyId) {
        return namedParameterJdbcTemplate.queryForObject(SQL_READ_PERSON, Collections.singletonMap("companyId", companyId), new CompanyRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deleteCompany(Integer companyId) {
        namedParameterJdbcTemplate.update(SQL_DELETE_PERSON, Collections.singletonMap("companyId", companyId));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updateCompany(Company company) {
        namedParameterJdbcTemplate.update(SQL_UPDATE_PERSON, new BeanPropertySqlParameterSource(company));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createCompany(Company company) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL_CREATE_PERSON, new BeanPropertySqlParameterSource(company), keyHolder);
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
}
