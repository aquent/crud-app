package com.aquent.crudapp.controllers;


import com.aquent.crudapp.dtos.ClientDTO;
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

    @GetMapping(value = "create")
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView("person/create");
        mav.addObject("person", new Person());
        mav.addObject("errors", new ArrayList<String>());
        return mav;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> create(@Valid @RequestBody PersonDTO personDTO) {
        List<String> errors = personService.validatePerson(personDTO);
        if  (null != errors && !errors.isEmpty())
            return generateResourceCreatedResponse(errors);

        Integer personId = personService.createPerson(personDTO);
        return generateResourceCreatedResponse(personId);
    }

//    /**
//     * Renders an edit form for an existing person record.
//     *
//     * @param personId the ID of the person to edit
//     * @return edit view populated from the person record
//     */
//    @GetMapping(value = "edit/{personId}")
//    public ModelAndView edit(@PathVariable Integer personId) {
//        ModelAndView mav = new ModelAndView("person/edit");
//        mav.addObject("person", personService.readPerson(personId));
//        mav.addObject("errors", new ArrayList<String>());
//        return mav;
//    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PersonDTO> getOne(@Valid @PathVariable("id") Integer id) {
        PersonDTO personDTO = personService.readPerson(id);
        if (null == personDTO) throw new ResourceNotFoundException(String.format(PERSON_NOT_FOUND, id));
        return generateResourceGetByIdResponseOk(personDTO);
    }

    @PutMapping(value = "edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> edit(PersonDTO personDTO) {
        List<String> errors = personService.validatePerson(personDTO);
        if  (null != errors && !errors.isEmpty())
            return generateResourceCreatedResponse(errors);

        PersonDTO addedPersonDTO = personService.updatePerson(personDTO);
        return generateResourceCreatedResponse(addedPersonDTO.getId());
    }

//    /**
//     * Renders the deletion confirmation page.
//     *
//     * @param personId the ID of the person to be deleted
//     * @return delete view populated from the person record
//     */
//    @GetMapping(value = "delete/{personId}")
//    public ModelAndView delete(@PathVariable long personId) {
//        ModelAndView mav = new ModelAndView("person/delete");
//        mav.addObject("person", personService.readPerson(personId));
//        return mav;
//    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable("id") long id) {
        personService.deletePerson(id);
        String message = String.format(PERSON_DELETED, id);
        return generateStringResponse(message);
    }
}
