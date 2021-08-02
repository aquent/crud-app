package com.aquent.crudapp.client;

//import com.sun.tools.javac.util.ArrayUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.TextUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Stream;

/**
 * Default implementation of {@link ClientService}.
 */
@Component
public class DefaultClientService implements ClientService {

    private final ClientDao clientDao;
    private final Validator validator;

    public DefaultClientService(ClientDao clientDao, Validator validator) {
        this.clientDao = clientDao;
        this.validator = validator;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Client> listClients() {
        return clientDao.listClients();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Client readClient(Integer id) {
        return clientDao.readClient(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createClient(Client client) {
        return clientDao.createClient(client);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updateClient(Client client) {
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

    @Override
    public int[] contactListAsArray(String contactListString) {
        return Stream.of(contactListString.split(",")).mapToInt(Integer::parseInt).toArray();
    }

    @Override
    public void removeContact(Client client, Integer personId) {
        String contactListString = client.getContactListString();
        int[] contactList = contactListAsArray(contactListString);

        int[] newContactList = new int[contactList.length - 1];

//        Remove the personId from the contact id list
        int index;

        int newListCount = 0;
        for (index = 0; index < contactList.length; index++) {
            int numToCheck = contactList[index];
            if (numToCheck == personId) {
//                do nothing
            } else {
                newContactList[newListCount] = contactList[index];
                newListCount++;
            }
        }

        String newListAsString = Arrays.toString(newContactList);

        client.setContactListString(newListAsString
                .replace("[", "")
                .replace("]", "")
                .replace(" ", ""));

        clientDao.updateClient(client) ;
    }


}
