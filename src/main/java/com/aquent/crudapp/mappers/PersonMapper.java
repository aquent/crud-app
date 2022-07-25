package com.aquent.crudapp.mappers;

import com.aquent.crudapp.dtos.PersonDTO;
import com.aquent.crudapp.models.Client;
import com.aquent.crudapp.models.Person;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {Person.class, Client.class})
@DecoratedWith(PersonMapperDecorator.class)
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mappings({
            @Mapping(target = "clientId", expression = "java(null != person.getClient() ? person.getClient().getId() : null)"),
            @Mapping(target = "clientName", expression = "java(null != person.getClient() ? person.getClient().getCompanyName() : null)")
    })
    PersonDTO toDto(Person person);

    @Mappings({
            @Mapping(target = "client.id", expression = "java(null != personDTO.getClientId() ? personDTO.getClientId() : null)"),
            @Mapping(target = "client.companyName", expression = "java(null != personDTO.getClientName() ? personDTO.getClientName() : null)")
    })
    Person toEntity(PersonDTO personDTO);

    List<PersonDTO> toDTOList(List<Person> contacts);
}
