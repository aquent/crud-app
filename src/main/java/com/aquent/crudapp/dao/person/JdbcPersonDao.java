package com.aquent.crudapp.dao.person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.aquent.crudapp.dto.Person;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring JDBC implementation of {@link PersonDao}.
 */
@Component
public class JdbcPersonDao implements PersonDao {

    private static final String SQL_LIST_PEOPLE = "SELECT p.*, c.client_name FROM person p left join client c on (p.client_id = c.client_id) ORDER BY first_name, last_name, person_id";
    private static final String SQL_LIST_CONTACTS_BY_NAME = "SELECT p.*, c.client_name FROM person p left join client c on (p.client_id = c.client_id) WHERE c.client_name = :clientName ORDER BY first_name, last_name, person_id";
    private static final String SQL_LIST_CONTACTS_BY_ID = "SELECT p.*, c.client_name FROM person p left join client c on (p.client_id = c.client_id) WHERE c.client_id = :clientId ORDER BY first_name, last_name, person_id";
    private static final String SQL_READ_PERSON = "SELECT * FROM person p left join client c on (p.client_id = c.client_id) WHERE p.person_id = :personId";
    private static final String SQL_DELETE_PERSON = "DELETE FROM person WHERE person_id = :personId";
    private static final String SQL_UPDATE_PERSON = "UPDATE person SET (first_name, last_name, email_address, street_address, city, state, zip_code, client_id)"
                                                  + " = (:firstName, :lastName, :emailAddress, :streetAddress, :city, :state, :zipCode, :clientName)"
                                                  + " WHERE person_id = :personId";
    private static final String SQL_CREATE_PERSON = "INSERT INTO person (first_name, last_name, email_address, street_address, city, state, zip_code, client_id)"
                                                  + " VALUES (:firstName, :lastName, :emailAddress, :streetAddress, :city, :state, :zipCode, :clientName)";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcPersonDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Person> listPeople() {
        return namedParameterJdbcTemplate.getJdbcOperations().query(SQL_LIST_PEOPLE, new PersonRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Person> listPeople(String clientName) {
        return namedParameterJdbcTemplate.query(SQL_LIST_CONTACTS_BY_NAME,
                Collections.singletonMap("clientName", clientName), new PersonRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Person> listPeople(Integer clientId) {
        return namedParameterJdbcTemplate.query(SQL_LIST_CONTACTS_BY_ID,
                Collections.singletonMap("clientId", clientId), new PersonRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Person readPerson(Integer personId) {
        return namedParameterJdbcTemplate.queryForObject(SQL_READ_PERSON, Collections.singletonMap("personId", personId), new PersonRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deletePerson(Integer personId) {
        namedParameterJdbcTemplate.update(SQL_DELETE_PERSON, Collections.singletonMap("personId", personId));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updatePerson(Person person) {
        namedParameterJdbcTemplate.update(SQL_UPDATE_PERSON, new BeanPropertySqlParameterSource(person));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createPerson(Person person) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL_CREATE_PERSON, new BeanPropertySqlParameterSource(person), keyHolder);
        return keyHolder.getKey().intValue();
    }

    /**
     * Row mapper for person records.
     */
    private static final class PersonRowMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setPersonId(rs.getInt("person_id"));
            person.setFirstName(rs.getString("first_name"));
            person.setLastName(rs.getString("last_name"));
            person.setEmailAddress(rs.getString("email_address"));
            person.setStreetAddress(rs.getString("street_address"));
            person.setCity(rs.getString("city"));
            person.setState(rs.getString("state"));
            person.setZipCode(rs.getString("zip_code"));
            person.setClientName(rs.getString("client_name"));
            return person;
        }
    }
}
