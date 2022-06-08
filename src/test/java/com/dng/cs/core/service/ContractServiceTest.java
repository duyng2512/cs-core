package com.dng.cs.core.service;

import com.dng.cs.core.entity.ClientEntity;
import com.dng.cs.core.entity.ContractEntity;
import com.dng.cs.core.model.Client;
import com.dng.cs.core.model.Contract;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ContractServiceTest {

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
    void updateContractById() {
    }

    @Test
    void getContractById() {
    }

    @Test
    void modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        ContractEntity entity = new ContractEntity();
        entity.setClientId(new ClientEntity());
        entity.setCardExpire("2309");

        Contract contract = new Contract();
        contract.setCardExpire("2501");

        modelMapper.map(contract, entity);
        assertEquals(entity.getCardExpire(), "2501");
    }
}