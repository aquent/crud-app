package com.aquent.crudapp.services;

import com.aquent.crudapp.dtos.ClientDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    List<ClientDTO> listClients();

    ClientDTO createClient(ClientDTO clientDTO);

    ClientDTO viewClient(Integer clientId);

    ClientDTO updateClient(ClientDTO clientDTO);

    void deleteClient(Integer clientId);

    /**
     * Validates populated client data.
     *
     * @param clientDTO the values to validate
     * @return list of error messages
     */
    List<String> validateClient(ClientDTO clientDTO);
}
