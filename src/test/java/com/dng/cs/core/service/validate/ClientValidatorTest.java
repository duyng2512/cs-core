package com.dng.cs.core.service.validate;

import com.dng.cs.core.model.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientValidatorTest {

    Client clientDTO = new Client();
    ClientValidator validator = new ClientValidator();

    @BeforeEach
    void setUp() {
        clientDTO.setClientCat("PRIVATE");
        clientDTO.setEmail("duyng@gmail.com");
        clientDTO.setPhone("0903818828");
        clientDTO.setState("ACTIVE");
        clientDTO.setIsReady("Y");
        clientDTO.setPhone("0900998828");
        clientDTO.setProductCat("ISS");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Disabled
    void validate() {
        validator.validate(clientDTO);
    }
}