package com.aquent.crudapp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aquent.crudapp.data.dao.PersonDao;
import com.aquent.crudapp.domain.Person;

/**
 * Default implementation of {@link PersonService}.
 */
public class DefaultPersonService implements PersonService {

    private PersonDao personDao;
    private Validator validator;

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Person> listPeople() {
        return personDao.listPeople();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Person> listPeople(Integer clientId) {
        return personDao.listPeople(clientId);
    }
    
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Person readPerson(Integer id) {
        return personDao.readPerson(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createPerson(Person person) {
        return personDao.createPerson(person);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updatePerson(Person person) {
        personDao.updatePerson(person);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deletePerson(Integer id) {
        personDao.deletePerson(id);
    }

    @Override
    public List<String> validatePerson(Person person) {
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        List<String> errors = new ArrayList<String>(violations.size());
        for (ConstraintViolation<Person> violation : violations) {
            errors.add(violation.getMessage());
        }
        Collections.sort(errors);
        return errors;
    }
}
