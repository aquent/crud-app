package com.aquent.crudapp.services;

import com.aquent.crudapp.dtos.ClientDTO;
import com.aquent.crudapp.dtos.PersonDTO;
import com.aquent.crudapp.errors.InvalidRequestException;
import com.aquent.crudapp.mappers.ClientMapper;
import com.aquent.crudapp.mappers.PersonMapper;
import com.aquent.crudapp.models.Client;
import com.aquent.crudapp.models.Person;
import com.aquent.crudapp.repositories.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

import static com.aquent.crudapp.utils.Constants.CLIENT_NOT_FOUND;

@Service
public class ClientServiceImpl implements ClientService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private Validator validator;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonMapper personMapper;

    @Override
    public List<ClientDTO> listClients() {
        List<Client> clients = new ArrayList<>();
        try {
            clients = clientRepository.findAll();
        } catch (Exception exception) {
            log.error("Error retrieving all the clients from the database: ", exception);
        }

        if (clients.isEmpty()) return new ArrayList<>();

        return clients
                .stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);

        List<PersonDTO> contactDTOs = clientDTO.getContacts();
        if (null != contactDTOs && !contactDTOs.isEmpty()) {
            List<Person> contacts = contactDTOs.stream()
                    .map(personMapper::toEntity)
                    .collect(Collectors.toList());
            client.setContacts(contacts);
        } else {
            client.setContacts(new ArrayList<>());
        }
        try {
            client = clientRepository.save(client);
        } catch (Exception exception) {
            log.error("Error saving the client {} to the database: ", client, exception);
            return null;
        }
        log.info("client saved with id {}", client.getId());
        clientDTO = clientMapper.toDto(client);
        log.info("clientDTO saved with id {}", clientDTO.getId());
        return clientDTO;
    }

    @Override
    public ClientDTO viewClient(Integer clientId) {
        Client client = null;
        try {
            client = clientRepository.getById(clientId);
        } catch (Exception exception) {
            log.error("Error retrieving the client by ID [{}] from the database: ", clientId, exception);
        }

        if  (null == client) return null;
        return clientMapper.toDto(client);
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) {
        List<PersonDTO> contactDTOs = clientDTO.getContacts();
        if (null != contactDTOs && !contactDTOs.isEmpty()) {
            List<PersonDTO> updatedContactDTOs = personService.updateContacts(contactDTOs, clientDTO.getId(), clientDTO.getCompanyName());
            clientDTO.setContacts(updatedContactDTOs);
        }
        Client client = clientMapper.toEntity(clientDTO);
        try {
            client = clientRepository.save(client);
        } catch (Exception exception) {
            log.error("Error updating the existing client {} in the database: ", client, exception);
            return null;
        }


        return clientMapper.toDto(client);
    }

    @Override
    public void deleteClient(Integer clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (null == client) throw new InvalidRequestException(String.format(CLIENT_NOT_FOUND, clientId));
        try {
            clientRepository.delete(client);
        } catch (Exception exception) {
            log.error("Error deleting the existing client with ID [{}] in the database: ", clientId, exception);
        }
    }

    @Override
    public List<String> validateClient(ClientDTO client) {
        Set<ConstraintViolation<ClientDTO>> violations = validator.validate(client);
        List<String> errors = new ArrayList<>(violations.size());
        for (ConstraintViolation<ClientDTO> violation : violations) {
            errors.add(violation.getMessage());
        }
        Collections.sort(errors);
        return errors;
    }
}
