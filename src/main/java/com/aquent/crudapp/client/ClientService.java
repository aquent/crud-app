package com.aquent.crudapp.client;

import com.aquent.crudapp.person.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    /**
     * Retrieves all of the client records.
     *
     * @return list of client records
     */
    List<Client> listClients();

    /**
     * Retrieves all of the person records related to the given client by Name.
     *
     * @return list of client records
     */
    List<Person> listContacts(String clientName);

    /**
     * Retrieves all of the person records related to the given client by ID.
     *
     * @return list of client records
     */
    List<Person> listContacts(Integer clientId);

    /**
     * Creates a new client record.
     *
     * @param client the values to save
     * @return the new client ID
     */
    Integer createClient(Client client);

    /**
     * Retrieves a client record by ID.
     *
     * @param id the client ID
     * @return the client record
     */
    Client readClient(Integer id);

    /**
     * Retrieves a client record by name.
     *
     * @param clientName the client name
     * @return the client record
     */
    Client readClient(String clientName);

    /**
     * Updates an existing client record.
     *
     * @param client the new values to save
     */
    void updateClient(Client client);

    /**
     * Deletes a client record by ID.
     *
     * @param id the client ID
     */
    void deleteClient(Integer id);

    /**
     * Validates populated client data.
     *
     * @param client the values to validate
     * @return list of error messages
     */
    List<String> validateClient(Client client);
}
