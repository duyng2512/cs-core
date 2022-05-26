package com.dng.cs.core.common;

import com.dng.cs.core.entity.ClientEntity;
import com.dng.cs.core.model.Client;
import com.dng.cs.core.util.TestUtil;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.test.annotation.Repeat;
import org.springframework.ui.ModelMap;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class CommonTest {

    @Test
    public void givenEntity_returnDTO() {
        ModelMapper modelMapper = new ModelMapper();
        Client client = modelMapper.map(new ClientEntity(), Client.class);
        assertThat(client).isNotNull();
    }

    @Test
    public void givenDTO_returnUpdateEntity() {
        ModelMapper modelMapper = new ModelMapper();
        // setup
        TypeMap<Client, ClientEntity> propertyMapper = modelMapper.createTypeMap(Client.class, ClientEntity.class);

        // add deep mapping to flatten source's Player Object into a single field in destination
        propertyMapper.addMappings(mapper -> mapper.map(Client::getEmail, ClientEntity::setEMail));


        Client client = new Client();
        client.setId(1000L);
        client.setEmail("update email");

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(1000L);
        client.setEmail("old email");

        modelMapper.map(client, clientEntity);
        assertThat(clientEntity.getEMail())
                .isEqualTo("update email");
    }

    @Test
    public void whenMapGameWithDeepMapping_thenConvertsToDTO() {
        ModelMapper modelMapper = new ModelMapper();

        // setup

        TypeMap<Client, ClientEntity> propertyMapper = modelMapper.createTypeMap(Client.class, ClientEntity.class);
        propertyMapper.addMappings(mapper -> mapper.map(Client::getEmail, ClientEntity::setEMail));

        // when map between different hierarchies
        Client client = new Client();
        client.setId(1000L);
        client.setEmail("update email");

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(1000L);
        clientEntity.setEMail("old email");

        modelMapper.map(client, clientEntity);
        // then
        assertThat(clientEntity.getEMail()).isEqualTo(client.getEmail());

        System.out.println("clientEntity " + clientEntity.getEMail());
        System.out.println("client " + client.getEmail());
    }

    @RepeatedTest(5)
    public void randomStringNumber_returnStringWithSpecificLen(){
        String result = TestUtil.randomStringNumber(10);
        System.out.println(result);
        assertThat(result.length()).isEqualTo(10);
    }

    @Test
    public void dateTime_java8(){
        OffsetDateTime time = OffsetDateTime.now();
        System.out.println(time.format(DateTimeFormatter.ISO_DATE_TIME));
    }
}
