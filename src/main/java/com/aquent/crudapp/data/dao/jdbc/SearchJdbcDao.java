package com.aquent.crudapp.data.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.aquent.crudapp.data.dao.SearchDao;
import com.aquent.crudapp.domain.Company;
import com.aquent.crudapp.domain.Person;
import com.aquent.crudapp.domain.SearchResult;

public class SearchJdbcDao implements SearchDao {

	private static final String SQL_COMPANY_SEARCH = "SELECT 'company' type, company_id, null person_id, name name, null first_name, null last_name, street_address, city, state, zip_code FROM company where LCASE(name) like :query";
	private static final String SQL_PERSON_SEARCH = "SELECT 'person' type, null company_id, person_id, null name, first_name, last_name, street_address, city, state, zip_code FROM person where LCASE(first_name) like :query or LCASE(last_name) like :query";
	private static final String SQL_SEARCH = "SELECT * FROM (" + SQL_COMPANY_SEARCH + " UNION ALL " + SQL_PERSON_SEARCH + " )";

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

	@Override
	public List<SearchResult> search(String query) {
		 return namedParameterJdbcTemplate.query(SQL_SEARCH, Collections.singletonMap("query", query), new SearchResultRowMapper());
	}

	/**
	 * Row mapper for search result records.
	 */
	private static final class SearchResultRowMapper implements RowMapper<SearchResult> {

		@Override
		public SearchResult mapRow(ResultSet rs, int rowNum) throws SQLException {
			SearchResult searchResult = new SearchResult();

			if ("company".equalsIgnoreCase(rs.getString("type"))) {
				Company company = new Company();
				company.setCompanyId(rs.getInt("company_id"));
				company.setName(rs.getString("name"));
				company.setStreetAddress(rs.getString("street_address"));
				company.setCity(rs.getString("city"));
				company.setState(rs.getString("state"));
				company.setZipCode(rs.getString("zip_code"));
				searchResult.setCompany(company);
			} else {
				Person person = new Person();
				person.setPersonId(rs.getInt("person_id"));
				person.setFirstName(rs.getString("first_name"));
				person.setLastName(rs.getString("last_name"));
				person.setStreetAddress(rs.getString("street_address"));
				person.setCity(rs.getString("city"));
				person.setState(rs.getString("state"));
				person.setZipCode(rs.getString("zip_code"));
				searchResult.setPerson(person);
			}

			return searchResult;
		}
	}

}
