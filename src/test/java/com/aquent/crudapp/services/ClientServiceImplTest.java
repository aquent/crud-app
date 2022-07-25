package com.aquent.crudapp.services;

import com.aquent.crudapp.dtos.ClientDTO;
import com.aquent.crudapp.mappers.ClientMapper;
import com.aquent.crudapp.mappers.PersonMapper;
import com.aquent.crudapp.models.Client;
import com.aquent.crudapp.repositories.ClientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ClientServiceImplTest {
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private Validator validator;

    @Mock
    private PersonService personService;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private ClientServiceImpl clientService;

    private Client client;
    private Client client2;
    private List<Client> clients;
    private ClientDTO clientDTO;
    private ClientDTO clientDTO2;
    private List<ClientDTO> expectedClientDTOList;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this.getClass());
        client = new Client();
        client.setId(10);
        client.setCompanyName("Test 1");

        client2 = new Client();
        client2.setId(11);
        clients = Arrays.asList(client, client2);

        clientDTO = new ClientDTO();
        clientDTO.setId(10);
        clientDTO.setCompanyName("Test 1");

        clientDTO2 = new ClientDTO();
        clientDTO2.setId(11);
        expectedClientDTOList = Arrays.asList(clientDTO, clientDTO2);
    }

    @Test
    public void test_listClients_return_emptyList() {
        doReturn(new ArrayList<>()).when(clientRepository).findAll();
        List<ClientDTO> clientDTOList = clientService.listClients();
        assertTrue(clientDTOList.isEmpty());
        verify(clientRepository, times(1)).findAll();
        verifyNoMoreInteractions(clientRepository);
    }

    @Test
    public void test_listClients_return_list() {
        doReturn(clients).when(clientRepository).findAll();
        doReturn(clientDTO).when(clientMapper).toDto(client);
        doReturn(clientDTO2).when(clientMapper).toDto(client2);
        List<ClientDTO> clientDTOList = clientService.listClients();
        assertEquals(expectedClientDTOList, clientDTOList);
        assertEquals(2, clientDTOList.size());
        verify(clientRepository, times(1)).findAll();
        verify(clientMapper, times(2)).toDto(any());
        verifyNoMoreInteractions(clientRepository);
        verifyNoMoreInteractions(clientMapper);
    }

}
