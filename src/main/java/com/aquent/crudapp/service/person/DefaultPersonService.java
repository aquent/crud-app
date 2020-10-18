package com.aquent.crudapp.service.person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.aquent.crudapp.dao.client.ClientDao;
import com.aquent.crudapp.dao.person.PersonDao;
import com.aquent.crudapp.dto.Person;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation of {@link PersonService}.
 */
@Component
public class DefaultPersonService implements PersonService {

    private final PersonDao personDao;
    private final ClientDao clientDao;
    private final Validator validator;

    public DefaultPersonService(PersonDao personDao, ClientDao clientDao, Validator validator) {
        this.personDao = personDao;
        this.clientDao = clientDao;
        this.validator = validator;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Person> listPeople() {
        return personDao.listPeople();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Person readPerson(Integer id) {
        return personDao.readPerson(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createPerson(Person person) {
        if (person.getClientName() != null) {
            person.setClientName("" + clientDao.readClient(person.getClientName()).getClientId());
        }
        return personDao.createPerson(person);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updatePerson(Person person) {
        System.out.println("service:" + person);
        if (person.getClientName() != null) {
            person.setClientName("" + clientDao.readClient(person.getClientName()).getClientId());
        }
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
