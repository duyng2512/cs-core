package com.dng.cs.core.service.validate;

import com.dng.cs.core.exception.InvalidReqBodyException;
import com.dng.cs.core.model.Address;
import com.dng.cs.core.model.Client;
import com.dng.cs.core.util.BusinessConstant;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class AddressValidator {


    private final Pattern phonePattern = Pattern.compile("^\\d{10}$");
    private final Pattern emailPattern = Pattern.compile("^(.+)@(\\S+)$");

    private final Pattern urlPattern = Pattern.compile("(www.)?"
                                                        + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                                                        + "{2,256}\\.[a-z]"
                                                        + "{2,6}\\b([-a-zA-Z0-9@:%"
                                                        + "._\\+~#?&//=]*)");

    public void validate(Address addressDTO) {
        String err;

        if (!addressDTO.getState().equals("A") && !addressDTO.getState().equals("I")) {
            err = String.format("State [%s] Only State ACTIVE or INACTIVE is valid", addressDTO.getState());
            throw new InvalidReqBodyException(err);
        }

        if (! (addressDTO.getZipcode().length() == BusinessConstant.ZIPCODE_LEN)) {
            err = String.format("Zipcode invalid [%s], length must be " + BusinessConstant.ZIPCODE_LEN, addressDTO.getZipcode());
            throw new InvalidReqBodyException(err);
        }

        if (!phonePattern.matcher(addressDTO.getPhone()).matches()) {
            err = String.format("Phone [%s] is invalid", addressDTO.getPhone());
            throw new InvalidReqBodyException(err);
        }

        if (!emailPattern.matcher(addressDTO.getEmail()).matches()) {
            err = String.format("Email Address [%s] is invalid", addressDTO.getEmail());
            throw new InvalidReqBodyException(err);
        }

        if (!urlPattern.matcher(addressDTO.getUrl()).matches()) {
            err = String.format("URL [%s] is invalid", addressDTO.getUrl());
            throw new InvalidReqBodyException(err);
        }
    };

}
