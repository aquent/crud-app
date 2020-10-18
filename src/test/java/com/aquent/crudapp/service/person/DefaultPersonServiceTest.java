package com.aquent.crudapp.service.person;

import com.aquent.crudapp.dto.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DefaultPersonServiceTest {

    @Autowired
    private DefaultPersonService defaultPersonService;
    private Person personA;

    @BeforeEach
    public void setUp() {
        personA = Person.builder()
                .firstName("Jeremy")
                // Note: Not a typo, this is last name in the musical :)
                .lastName("Heere")
                .emailAddress("robsam@fakeaccnt.com")
                .streetAddress("123 fake st.")
                .city("Seattle")
                .state("WA")
                .zipCode("12345")
                .clientName(null)
                .build();

        int id = defaultPersonService.createPerson(personA);
        personA.setPersonId(id);
    }

    @AfterEach
    public void tearDown() {
        defaultPersonService.deletePerson(personA.getPersonId());
    }

    @Test
    void listPeople() {
        List<Person> people = defaultPersonService.listPeople();
        int prevSize = people.size();
        Person person = Person.builder()
                .firstName("Christine")
                .lastName("Canigula")
                .emailAddress("ccanigula@fakeaccnt.com")
                .streetAddress("25 squip st.")
                .city("Newark")
                .state("NJ")
                .zipCode("12345")
                .clientName("Aquent")
                .build();

        int id = defaultPersonService.createPerson(person);
        people = defaultPersonService.listPeople();
        assertEquals(prevSize + 1, people.size());
    }

    @Test
    void readPerson() {
        Person jeremy = defaultPersonService.readPerson(personA.getPersonId());
        assertEquals(personA, jeremy);
    }

    @Test
    void updatePerson() {
        String newLastName = "Hansen";
        personA.setLastName(newLastName);
        defaultPersonService.updatePerson(personA);
        Person retrievedPerson = defaultPersonService.readPerson(personA.getPersonId());
        assertEquals(newLastName, retrievedPerson.getLastName());
    }

//    @Test
//    void validatePerson() {
//    }
}