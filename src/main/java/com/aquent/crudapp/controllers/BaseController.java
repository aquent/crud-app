package com.aquent.crudapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * This class acts as base class for all the controllers
 * @param <T>
 * */
public abstract class BaseController<T> {

    /**
     * This method generates the no content response for read by id operation
     * @param entity
     * */
    protected ResponseEntity<T> generateResourceGetByIdNoContentResponse(T entity) {
        return new ResponseEntity<>(entity, HttpStatus.NO_CONTENT);
    }

    /**
     * This method generates the no content response for read all operation
     * @param entities
     * */
    protected ResponseEntity<List<T>> generateResourceGetAllNoContentResponse(List<T> entities) {
        return new ResponseEntity<>(entities, HttpStatus.NO_CONTENT);
    }

    /**
     * This method generates the required response for read by id operation
     * @param entity
     * */
    protected ResponseEntity<T> generateResourceGetByIdResponseOk(T entity) {
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    /**
     * This method generates the required response for read all operation
     * @param entities
     * */
    protected ResponseEntity<List<T>> generateResourceGetAllResponseOk(List<T> entities) {
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    /**
     * This method generates the required response for create operation
     * @param id
     * */
    protected ResponseEntity<T> generateResourceCreatedResponse(Integer id) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();

        return ResponseEntity.created(location).build();
    }

    protected ResponseEntity<T> generateResourceCreatedResponse() {
        return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
    }

    /**
     * This method generates the errors response for create operation
     * @param errors
     * */
    protected ResponseEntity<List<T>> generateResourceCreatedResponse(List<T> errors) {
        return new ResponseEntity<>(errors, HttpStatus.OK);
    }

    /**
     * This method generates the required response for delete operation
     * @param message
     * */
    protected ResponseEntity<T> generateStringResponse(T message) {
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
