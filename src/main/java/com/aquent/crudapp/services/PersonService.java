package com.aquent.crudapp.services;

import com.aquent.crudapp.dtos.PersonDTO;
import com.aquent.crudapp.models.Person;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Person operations.
 */
@Service
public interface PersonService {

    List<PersonDTO> listPeople();

    /**
     * Creates a new person record.
     *
     * @param person the values to save
     */
    Integer createPerson(PersonDTO person);

    /**
     * Retrieves a person record by ID.
     *
     * @param id the person ID
     * @return the person record
     */
    PersonDTO readPerson(Integer id);

    /**
     * Updates an existing person record.
     *
     * @param personDTO the new values to save
     */
    PersonDTO updatePerson(PersonDTO personDTO);

    /**
     * Deletes a person record by ID.
     *
     * @param id the person ID
     */
    void deletePerson(long id);

    /**
     * Validates populated person data.
     *
     * @param personDTO the values to validate
     * @return list of error messages
     */
    List<String> validatePerson(PersonDTO personDTO);
}
