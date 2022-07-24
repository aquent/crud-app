package com.aquent.crudapp.controllers;

import com.aquent.crudapp.dtos.ClientDTO;
import com.aquent.crudapp.services.ClientService;
import com.aquent.crudapp.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.aquent.crudapp.utils.Constants.CLIENT_DELETED;
import static com.aquent.crudapp.utils.Constants.CLIENT_NOT_FOUND;

/**
 * Controller for handling basic person management operations.
 */
@RestController
@RequestMapping(path = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ClientController extends BaseController {
    @Autowired
    ClientService clientService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clients = clientService.listClients();
        if (null == clients || clients.isEmpty())
            return generateResourceGetAllNoContentResponse(clients);
        else
            return generateResourceGetAllResponseOk(clients);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClientDTO> getOne(@Valid @PathVariable("id") Integer id) {
        ClientDTO client = clientService.viewClient(id);
        if (null == client) throw new ResourceNotFoundException(String.format(CLIENT_NOT_FOUND, id));
        return generateResourceGetByIdResponseOk(client);
    }

    @PostMapping(path = "/create",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@Valid @RequestBody ClientDTO clientDTO) {
        List<String> errors = clientService.validateClient(clientDTO);

        if  (null != errors && !errors.isEmpty())
            return generateResourceCreatedResponse(errors);

        ClientDTO savedClientDTO = clientService.createClient(clientDTO);
        if (null == savedClientDTO)
            return generateResourceCreatedResponse();

        return generateResourceCreatedResponse(savedClientDTO.getId());
    }

    @PutMapping(path = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDTO> update(@Valid @RequestBody ClientDTO clientDTO) {
        List<String> errors = clientService.validateClient(clientDTO);

        if  (null != errors && !errors.isEmpty())
            return generateResourceCreatedResponse(errors);

        ClientDTO updatedClient = clientService.updateClient(clientDTO);
        return generateResourceCreatedResponse(updatedClient.getId());
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable("id") Integer id) {
        clientService.deleteClient(id);
        String message = String.format(CLIENT_DELETED, id);
        return generateStringResponse(message);
    }

}
