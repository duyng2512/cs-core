package com.dng.cs.core.service;

import com.dng.cs.core.entity.ClientEntity;
import com.dng.cs.core.exception.BusinessConstraintException;
import com.dng.cs.core.exception.InvalidReqBodyException;
import com.dng.cs.core.model.Client;
import com.dng.cs.core.repository.base.ClientBaseRepository;
import com.dng.cs.core.service.validate.ClientValidator;
import org.joda.time.DateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientBaseRepository clientBaseRepository;
    @Mock
    private ClientValidator clientValidator;
    @Mock
    private ModelMapper modelMapper = new ModelMapper();
    @InjectMocks
    private ClientService clientService;

    Client clientDTO = new Client();

    public ClientServiceTest() {
        clientDTO.setClientCat("PRIVATE");
        clientDTO.setEmail("duyng@gmail.com");
        clientDTO.setPhone("0903818828");
        clientDTO.setState("ACTIVE");
        clientDTO.setIsReady("Y");
        clientDTO.setPhone("0900998828");
        clientDTO.setProductCat("ISS");
        clientDTO.setAddInfo("Add info");
    }

    @Test
    void addClient_validObject_returnVoid() {

        ClientEntity entity = new ClientEntity();
        entity.setId(1000);

        Mockito.when(clientBaseRepository.existsClientEntityByRegNumber(Mockito.any()))
                .thenReturn(Boolean.FALSE);
        Mockito.when(clientBaseRepository.save(Mockito.any()))
                        .thenReturn(entity);
        Long newId = clientService.addClient(clientDTO);
    }


    @Test
    void addClient_invalidId_throwException() {
        clientDTO.setId(100L);
        try {
            clientService.addClient(clientDTO);
        } catch (InvalidReqBodyException exception){
            System.err.println(exception.getMessage());
        }

        // Clean up
        clientDTO.setId(null);
    }

    @Test
    void addClient_invalidRegNumber_throwException() {

        Mockito.when(clientBaseRepository.existsClientEntityByRegNumber(Mockito.any()))
                .thenReturn(Boolean.TRUE);
        try {
            clientService.addClient(clientDTO);
        } catch (BusinessConstraintException exception){
            System.err.println(exception.getMessage());
        }
    }


    @Test
    void mapping_dto_to_entity (){
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Client, ClientEntity> emailMapper = modelMapper.createTypeMap(Client.class, ClientEntity.class);
        emailMapper.addMappings(mapper -> mapper.map(Client::getEmail, ClientEntity::setEMail));

        Client dto = new Client();
        dto.setDateCreated(OffsetDateTime.now());
        dto.setPhone("09099299992");
        dto.setId(100L);
        dto.setClientName("New name");

        ClientEntity entity = new ClientEntity();
        entity.setId(100L);
        entity.setPhone("XXXXXXXXXXX");
        entity.setClientName("Old name");

        modelMapper.map(dto, entity);
        assertThat(entity.getClientName()).isEqualTo("New name");

    }

}