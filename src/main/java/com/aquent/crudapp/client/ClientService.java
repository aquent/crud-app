package com.aquent.crudapp.client;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Client operations.
 */
@Service
public interface ClientService {

    /**
     * Retrieves all of the client records.
     *
     * @return list of client records
     */
    List<Client> listClients();

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

    /**
     * Gets the contactListString.
     *
     * @return list of integers as clientId
     */
    int[] contactListAsArray(String contactListString);

//    todo document
    void removeContact(Client client, Integer personId);
}
