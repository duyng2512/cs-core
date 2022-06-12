package com.dng.cs.core.service;

import com.dng.cs.core.entity.ClientEntity;
import com.dng.cs.core.exception.BusinessConstraintException;
import com.dng.cs.core.exception.InvalidReqBodyException;
import com.dng.cs.core.model.Client;
import com.dng.cs.core.repository.base.ClientBaseRepository;
import com.dng.cs.core.service.validate.ClientValidator;
import com.dng.cs.core.util.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.joda.time.DateTime;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Disabled
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
    @Disabled
    void mapping_dto_to_entity (){
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Client, ClientEntity> typeMap = modelMapper.createTypeMap(Client.class, ClientEntity.class);

        Converter<OffsetDateTime, Instant> dateConverter = mappingContext -> mappingContext.getSource().toInstant();
        Converter<String, String> uppercaseConverter = mappingContext -> mappingContext.getSource().toUpperCase(Locale.ROOT);

        typeMap.addMappings(map -> map.using(uppercaseConverter).map(Client::getClientName, ClientEntity::setClientName));
        typeMap.addMappings(map -> map.using(dateConverter).map(Client::getDateCreated, ClientEntity::setDateCreated));
        typeMap.addMappings(map -> map.using(dateConverter).map(Client::getAddDate, ClientEntity::setAddDate));

        Client dto = new Client();
        dto.setPhone("09099299992");
        dto.setId(100L);
        dto.setClientName("New name");

        ClientEntity entity = new ClientEntity();
        entity.setId(100L);
        entity.setPhone("090992222222");
        entity.setClientName("Old name");

        modelMapper.map(dto, entity);
        assertThat(entity.getClientName()).isEqualTo("New name".toUpperCase(Locale.ROOT));
        assertThat(entity.getDateCreated()).isNotNull();
    }

    @Test
    void mapping_from_object_to_string() throws JsonProcessingException {
        ObjectMapper mapper = JsonMapper.builder()
                                        .addModule(new JavaTimeModule())
                                        .build();
        mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
        Client client = new Client();
        client.setClientName("Duy Nguyen");
        client.setRegNumber(TestUtil.randomStringNumber(9));
        client.setPhone(TestUtil.randomStringNumber(10));
        client.setClientCat("PRIVATE");
        client.setEmail("duyng@gmail.com");
        client.setProductCat("ISS");
        client.setIsReady("Y");
        client.setState("ACTIVE");
        String bodyReq = mapper.writeValueAsString(client);
        System.out.println(bodyReq);
    }

}