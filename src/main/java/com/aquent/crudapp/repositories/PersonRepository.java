package com.aquent.crudapp.repositories;


import com.aquent.crudapp.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Operations on the "person" table.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person getById(Integer id);
}
