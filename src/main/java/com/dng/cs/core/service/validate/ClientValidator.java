package com.dng.cs.core.service.validate;

import com.dng.cs.core.exception.InvalidReqBodyException;
import com.dng.cs.core.model.Client;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ClientValidator {

    private final Pattern phonePattern = Pattern.compile("^\\d{10}$");
    private final Pattern emailPattern = Pattern.compile("^(.+)@(\\S+)$");

    public void validate(Client clientDTO) {
        String err;

        if (!clientDTO.getProductCat().equals("ISS") && !clientDTO.getProductCat().equals("ACQ")) {
            err = String.format("Invalid [%s], Only Product Category ISS or ACQ is valid", clientDTO.getProductCat());
            throw new InvalidReqBodyException(err);
        }

        if (!clientDTO.getState().equals("ACTIVE") && !clientDTO.getState().equals("INACTIVE")) {
            err = String.format("Invalid [%s], Only Product Category ISS or ACQ is valid", clientDTO.getState());
            throw new InvalidReqBodyException(err);
        }

        if (!clientDTO.getClientCat().equals("PRIVATE") && !clientDTO.getClientCat().equals("COMMERCIAL")) {
            err = String.format("Invalid [%s], Only Client Category PRIVATE or COMMERCIAL is valid", clientDTO.getClientCat());
            throw new InvalidReqBodyException(err);
        }

        if (!phonePattern.matcher(clientDTO.getPhone()).matches()) {
            err = String.format("Phone %s is invalid", clientDTO.getPhone() );
            throw new InvalidReqBodyException(err);
        }

        if (!emailPattern.matcher(clientDTO.getEmail()).matches()) {
            err = String.format("Email %s is invalid", clientDTO.getEmail());
            throw new InvalidReqBodyException(err);
        }
    };


}
