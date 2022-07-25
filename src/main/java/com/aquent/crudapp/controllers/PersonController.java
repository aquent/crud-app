package com.aquent.crudapp.controllers;


import com.aquent.crudapp.dtos.PersonDTO;
import com.aquent.crudapp.errors.ResourceNotFoundException;
import com.aquent.crudapp.models.Person;
import com.aquent.crudapp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.aquent.crudapp.utils.Constants.*;

/**
 * Controller for handling basic person management operations.
 */
@RestController
@RequestMapping(path = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class PersonController extends BaseController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<PersonDTO>> getAllPeople() {
        List<PersonDTO> people = personService.listPeople();
        if (null == people || people.isEmpty())
            return generateResourceGetAllNoContentResponse(people);
        else
            return generateResourceGetAllResponseOk(people);
    }

//    @GetMapping(value = "/list/unemployed/{clientId}")
//    public ResponseEntity<List<PersonDTO>> getAllUnployedPeople(@Valid @PathVariable("clientId") Integer clientId) {
//        List<PersonDTO> people = personService.getUnemployedPeopleWithClientId(clientId);
//        if (null == people || people.isEmpty())
//            return generateResourceGetAllNoContentResponse(people);
//        else
//            return generateResourceGetAllResponseOk(people);
//    }

    @GetMapping(value = "create")
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView("person/create");
        mav.addObject("person", new Person());
        mav.addObject("errors", new ArrayList<String>());
        return mav;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> create(@Valid @RequestBody PersonDTO personDTO) {
        List<String> errors = personService.validatePerson(personDTO);
        if  (null != errors && !errors.isEmpty())
            return generateResourceCreatedResponse(errors);

        Integer personId = personService.createPerson(personDTO);
        return generateResourceCreatedResponse(personId);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PersonDTO> getOne(@Valid @PathVariable("id") Integer id) {
        PersonDTO personDTO = personService.readPerson(id);
        if (null == personDTO) throw new ResourceNotFoundException(String.format(PERSON_NOT_FOUND, id));
        return generateResourceGetByIdResponseOk(personDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> edit(@Valid @RequestBody PersonDTO personDTO) {
        List<String> errors = personService.validatePerson(personDTO);
        if  (null != errors && !errors.isEmpty())
            return generateResourceCreatedResponse(errors);

        PersonDTO addedPersonDTO = personService.updatePerson(personDTO);
        return generateResourceCreatedResponse(addedPersonDTO.getId());
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable("id") Integer id) {
        personService.deletePerson(id);
        String message = String.format(PERSON_DELETED, id);
        return generateStringResponse(message);
    }
}
