package com.aquent.crudapp.services;

import com.aquent.crudapp.dtos.PersonDTO;
import com.aquent.crudapp.errors.InvalidRequestException;
import com.aquent.crudapp.mappers.PersonMapper;
import com.aquent.crudapp.models.Person;
import com.aquent.crudapp.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.aquent.crudapp.utils.Constants.PERSON_NOT_FOUND;

@Service
public class PersonServiceImpl implements PersonService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private Validator validator;

    @Override
    public List<PersonDTO> listPeople() {
        List<Person> people = new ArrayList<>();
        try {
            people = personRepository.findAll();
        } catch (Exception exception) {
            log.error("Error retrieving all the people from the database: ", exception);
        }

        if (people.isEmpty()) return new ArrayList<>();
        return people.stream()
                .map(personMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonDTO> getUnemployedPeopleWithClientId(Integer clientId) {
        List<Person> people = new ArrayList<>();
        try {
            people = personRepository.findAllByClientIsNullOrClient_Id(clientId);
        } catch (Exception exception) {
            log.error("Error retrieving all the people from the database: ", exception);
        }

        if (people.isEmpty()) return new ArrayList<>();
        return people.stream()
                .map(personMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Integer createPerson(PersonDTO personDTO) {
        Person person = personMapper.toEntity(personDTO);
        try {
            person = personRepository.save(person);
        } catch (Exception exception) {
            log.error("Error saving the person {} to the database: ", person, exception);
        }
        return person.getId();
    }

    @Override
    public PersonDTO readPerson(Integer id) {
        Person person = null;
        try {
            person = personRepository.getById(id);
        } catch (Exception exception) {
            log.error("Error retrieving the person by ID [{}] from the database: ", id, exception);
        }
        if (null == person) return null;
        return personMapper.toDto(person);
    }

    @Override
    public PersonDTO updatePerson(PersonDTO personDTO) {
        Person person = personMapper.toEntity(personDTO);
        try {
            person = personRepository.save(person);
        } catch (Exception exception) {
            log.error("Error updating the existing person {} in the database: ", person, exception);
            return null;
        }
        return personMapper.toDto(person);
    }

    @Override
    public void deletePerson(Integer id) {
        Person person = personRepository.getById(id);
        if (null == person) throw new InvalidRequestException(String.format(PERSON_NOT_FOUND, id));
        try {
            personRepository.delete(person);
        } catch (Exception exception) {
            log.error("Error deleting the existing person with ID [{}] in the database: ", id, exception);
        }
    }

    @Override
    public List<String> validatePerson(PersonDTO personDTO) {
        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(personDTO);
        List<String> errors = new ArrayList<>(violations.size());
        for (ConstraintViolation<PersonDTO> violation : violations) {
            errors.add(violation.getMessage());
        }
        Collections.sort(errors);
        return errors;
    }

    @Override
    public List<PersonDTO> updateContacts(List<PersonDTO> contactDTOs, Integer clientId, String clientName) {
        List<Person> peopleToUpdate = new ArrayList<>();
        List<PersonDTO> existingContactDTOs = getContactsByClientId(clientId);
        List<Integer> contactDTOIds = contactDTOs.stream()
                .map(PersonDTO::getId)
                .collect(Collectors.toList());

        // add new contacts to client
        contactDTOs.forEach(contactDTO -> {
            // update contactDTO
            contactDTO.setClientId(clientId);
            contactDTO.setClientName(clientName);

            // map contactDTO to person and add to peopleToUpdate list
            Person person = personMapper.toEntity(contactDTO);
            peopleToUpdate.add(person);
        });

        // delete existing contacts from client
        existingContactDTOs.forEach(existingContactDTO -> {
            if (!contactDTOIds.contains(existingContactDTO.getId())) {
                Person person = personMapper.toEntity(existingContactDTO);
                person.setClient(null);
                peopleToUpdate.add(person);
            }
        });

        personRepository.saveAll(peopleToUpdate);
        return contactDTOs;
    }

    @Override
    public List<PersonDTO> getContactsByClientId(Integer clientId) {
        List<Person> contacts = personRepository.findAllByClient_Id(clientId);
        if (contacts.isEmpty()) return new ArrayList<>();
        return contacts.stream()
                .map(personMapper::toDto)
                .collect(Collectors.toList());
    }
}
