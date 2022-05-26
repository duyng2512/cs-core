package com.dng.cs.core.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(AddressController.class)
@TestPropertySource(locations="classpath:application-test.properties")
class ContractControllerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addContract() {
    }

    @Test
    void deleteContractById() {
    }

    @Test
    void getContractById() {
    }

    @Test
    void updateContractById() {
    }
}