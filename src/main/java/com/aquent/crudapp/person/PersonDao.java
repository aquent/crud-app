package com.aquent.crudapp.person;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Operations on the "person" table.
 */
@Repository
public interface PersonDao {

    /**
     * Retrieves all of the person records.
     *
     * @return list of person records
     */
    List<Person> listPeople();

    /**
     * Retrieves all of the person records related to a specific client by name.
     *
     * @return list of person records
     */
    List<Person> listPeople(String clientName);

    /**
     * Retrieves all of the person records related to a specific client by id.
     *
     * @return list of person records
     */
    List<Person> listPeople(Integer clientId);

    /**
     * Creates a new person record.
     *
     * @param person the values to save
     * @return the new person ID
     */
    Integer createPerson(Person person);

    /**
     * Retrieves a person record by ID.
     *
     * @param id the person ID
     * @return the person record
     */
    Person readPerson(Integer id);

    /**
     * Updates an existing person record.
     *
     * @param person the new values to save
     */
    void updatePerson(Person person);

    /**
     * Deletes a person record by ID.
     *
     * @param id the person ID
     */
    void deletePerson(Integer id);
}
