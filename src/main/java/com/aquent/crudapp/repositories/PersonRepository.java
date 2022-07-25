package com.aquent.crudapp.repositories;


import com.aquent.crudapp.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Operations on the "person" table.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person getById(Integer id);

    @Query("select p from Person p where p.client.id = (:clientId) or p.client.id is null")
    List<Person> findAllByClientIsNullOrClient_Id(@Param("clientId") Integer clientId);

    List<Person> findAllByClient_Id(Integer clientId);
}
