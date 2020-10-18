package com.aquent.crudapp.service.client;

import com.aquent.crudapp.dto.Client;
import com.aquent.crudapp.dao.client.ClientDao;
import com.aquent.crudapp.dto.Person;
import com.aquent.crudapp.dao.person.PersonDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Default implementation of {@link ClientService}.
 */
@Component
public class DefaultClientService implements ClientService {

    private final ClientDao clientDao;
    private final PersonDao personDao;
    private final Validator validator;

    public DefaultClientService(ClientDao clientDao, PersonDao personDao, Validator validator) {
        this.clientDao = clientDao;
        this.personDao = personDao;
        this.validator = validator;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Client> listClients() {
        return clientDao.listClients();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Person> listContacts(String clientName) {
        return personDao.listPeople(clientName);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Person> listContacts(Integer clientId) {
        return personDao.listPeople(clientId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Client readClient(Integer id) {
        return clientDao.readClient(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Client readClient(String clientName) {
        return clientDao.readClient(clientName);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createClient(Client client) {
        return clientDao.createClient(client);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updateClient(Client client) {
        List<Person> allPeople = personDao.listPeople();
        for (Person person : allPeople) {
            if (client.getContacts().contains(person)) {
                // TODO: Do something about this?
                if (!person.getClientName().equals(client.getClientName())) {
                    person.setClientName("" + client.getClientId());
                    personDao.updatePerson(person);
                }
            } else {
                if (person.getClientName().equals(client.getClientName())) {
                    person.setClientName(null);
                    personDao.updatePerson(person);
                }
            }
        }
        clientDao.updateClient(client);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deleteClient(Integer id) {
        clientDao.deleteClient(id);
    }

    @Override
    public List<String> validateClient(Client client) {
        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        List<String> errors = new ArrayList<String>(violations.size());
        for (ConstraintViolation<Client> violation : violations) {
            errors.add(violation.getMessage());
        }
        Collections.sort(errors);
        return errors;
    }
}
