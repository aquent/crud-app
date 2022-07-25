package com.aquent.crudapp.mappers;

import com.aquent.crudapp.dtos.ClientDTO;
import com.aquent.crudapp.dtos.PersonDTO;
import com.aquent.crudapp.models.Client;
import com.aquent.crudapp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientMapperDecorator implements ClientMapper {

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private PersonMapper personMapper;

    @Override
    public ClientDTO toDto(Client client) {
        List<Person> contacts = client.getContacts();
        ClientDTO clientDTO = clientMapper.toDto(client);
        if (null == contacts || contacts.isEmpty()) {
            clientDTO.setContacts(new ArrayList<>());
        } else {
            List<PersonDTO> contactDTOs = contacts.stream()
                    .map(personMapper::toDto)
                    .collect(Collectors.toList());
            clientDTO.setContacts(contactDTOs);
        }

        return clientDTO;
    }

    @Override
    public Client toEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId( clientDTO.getId() );
        client.setCompanyName( clientDTO.getCompanyName() );
        client.setWebsiteURI( clientDTO.getWebsiteURI() );
        client.setPhoneNumber( clientDTO.getPhoneNumber() );
        client.setStreetAddress( clientDTO.getStreetAddress() );
        client.setCity( clientDTO.getCity() );
        client.setState( clientDTO.getState() );
        client.setZipCode( clientDTO.getZipCode() );

        List<PersonDTO> contactDTOs = clientDTO.getContacts();
        if (null == contactDTOs || contactDTOs.isEmpty()) {
            client.setContacts(new ArrayList<>());
        } else {
            List<Person> contacts = contactDTOs.stream()
                    .map(personMapper::toEntity)
                    .collect(Collectors.toList());
            client.setContacts(contacts);
        }

        return client;
    }

    @Override
    public List<ClientDTO> toDTOList(List<Client> clients) {
        if (null == clients || clients.isEmpty()) return new ArrayList<>();

        return clients.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
