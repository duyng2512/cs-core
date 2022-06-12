package com.dng.cs.core.service.validate;

import com.dng.cs.core.exception.InvalidReqBodyException;
import com.dng.cs.core.model.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressValidatorTest {

    AddressValidator validator = new AddressValidator();
    Address address = new Address();

    @BeforeEach
    void beforeEach(){
        address.setAddressLine("CMT8");
        address.setAddressType("HOME");
        address.setClientId(1000L);
        address.setEmail("duyng@gmail.com");
        address.setUrl("www.google.com");
        address.setPhone("0903818828");
        address.setZipcode("700000");
        address.setState("A");
    }

    @Test
    void validate_validObject_returnVoid() {
        validator.validate(address);
    }

    @Test
    void validate_phoneError_returnException() {
        address.setPhone("This is wrong phone");
        try {
            validator.validate(address);
        } catch (InvalidReqBodyException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Test
    void validate_emailError_returnException() {
        address.setEmail("This is wrong email");
        try {
            validator.validate(address);
        } catch (InvalidReqBodyException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Test
    void validate_zipCodeError_returnException() {
        address.setZipcode("9000000");
        try {
            validator.validate(address);
        } catch (InvalidReqBodyException ex) {
            System.err.println(ex.getMessage());
        }
    }
}