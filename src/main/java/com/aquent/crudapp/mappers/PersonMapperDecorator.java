package com.aquent.crudapp.mappers;

import com.aquent.crudapp.dtos.PersonDTO;
import com.aquent.crudapp.models.Client;
import com.aquent.crudapp.models.Person;
import com.aquent.crudapp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PersonMapperDecorator implements PersonMapper {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public PersonDTO toDto(Person person) {
        return personMapper.toDto(person);
    }

    @Override
    public Person toEntity(PersonDTO personDTO) {
        Person person = new Person();
        person.setId( personDTO.getId() );
        person.setFirstName( personDTO.getFirstName() );
        person.setLastName( personDTO.getLastName() );
        person.setEmailAddress( personDTO.getEmailAddress() );
        person.setStreetAddress( personDTO.getStreetAddress() );
        person.setCity( personDTO.getCity() );
        person.setState( personDTO.getState() );
        person.setZipCode( personDTO.getZipCode() );

        Integer clientId = personDTO.getClientId();
        if (null == clientId) {
            person.setClient(null);
        } else {
            Client client = clientRepository.getById(clientId);
            person.setClient(client);
        }
        return person;
    }

    @Override
    public List<PersonDTO> toDTOList(List<Person> contacts) {
        return personMapper.toDTOList(contacts);
    }
}
