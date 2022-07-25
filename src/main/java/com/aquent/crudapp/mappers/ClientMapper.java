package com.aquent.crudapp.mappers;

import com.aquent.crudapp.dtos.ClientDTO;
import com.aquent.crudapp.models.Client;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring", uses = {Client.class})
@DecoratedWith(ClientMapperDecorator.class)
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mappings({
            @Mapping(source = "contacts", target = "contacts")
    })
    ClientDTO toDto(Client client);

    @Mappings({
            @Mapping(source = "contacts", target = "contacts")
    })
    Client toEntity(ClientDTO clientDTO);

    List<ClientDTO> toDTOList(List<Client> clients);
}
