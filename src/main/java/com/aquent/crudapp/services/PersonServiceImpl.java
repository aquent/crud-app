package com.aquent.crudapp.services;

import com.aquent.crudapp.dtos.PersonDTO;
import com.aquent.crudapp.errors.InvalidRequestException;
import com.aquent.crudapp.mappers.PersonMapper;
import com.aquent.crudapp.models.Person;
import com.aquent.crudapp.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.aquent.crudapp.utils.Constants.PERSON_NOT_FOUND;

@Service
public class PersonServiceImpl implements PersonService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private Validator validator;

    @Override
    public List<PersonDTO> listPeople() {
        List<Person> people = new ArrayList<>();
        try {
            people = personRepository.findAll();
        } catch (Exception exception) {
            log.error("Error retrieving all the people from the database: ", exception);
        }

        if (people.isEmpty()) return new ArrayList<>();
        return people.stream()
                .map(personMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Integer createPerson(PersonDTO personDTO) {
        Person person = personMapper.toEntity(personDTO);
        try {
            person = personRepository.save(person);
        } catch (Exception exception) {
            log.error("Error saving the person {} to the database: ", person, exception);
        }
        return person.getId();
    }

    @Override
    public PersonDTO readPerson(Integer id) {
        Person person = null;
        try {
            person = personRepository.getById(id);
        } catch (Exception exception) {
            log.error("Error retrieving the person by ID [{}] from the database: ", id, exception);
        }
        if (null == person) return null;
        return personMapper.toDto(person);
    }

    @Override
    public PersonDTO updatePerson(PersonDTO personDTO) {
        Person person = personMapper.toEntity(personDTO);
        try {
            person = personRepository.saveAndFlush(person);
        } catch (Exception exception) {
            log.error("Error updating the existing person {} in the database: ", person, exception);
            return null;
        }
        return personMapper.toDto(person);
    }

    @Override
    public void deletePerson(long id) {
        Person person = personRepository.findById(id).orElse(null);
        if (null == person) throw new InvalidRequestException(String.format(PERSON_NOT_FOUND, id));
        try {
            personRepository.delete(person);
        } catch (Exception exception) {
            log.error("Error deleting the existing person with ID [{}] in the database: ", id, exception);
        }
    }

    @Override
    public List<String> validatePerson(PersonDTO personDTO) {
        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(personDTO);
        List<String> errors = new ArrayList<>(violations.size());
        for (ConstraintViolation<PersonDTO> violation : violations) {
            errors.add(violation.getMessage());
        }
        Collections.sort(errors);
        return errors;
    }
}
