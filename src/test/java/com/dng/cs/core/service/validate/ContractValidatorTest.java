package com.dng.cs.core.service.validate;

import com.dng.cs.core.entity.ClientEntity;
import com.dng.cs.core.entity.ContractEntity;
import com.dng.cs.core.model.Contract;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContractValidatorTest {

    @Autowired
    @Qualifier("contractModelMapper")
    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void validate() {
        ContractEntity entity = new ContractEntity();
        entity.setStatus("00");

        Contract contract = modelMapper.map(entity, Contract.class);
        System.out.println(contract);
    }
}