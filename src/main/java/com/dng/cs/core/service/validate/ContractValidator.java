package com.dng.cs.core.service.validate;

import com.dng.cs.core.exception.InvalidReqBodyException;
import com.dng.cs.core.model.Contract;
import org.springframework.stereotype.Component;

@Component
public class ContractValidator {

    public void validate(Contract contractDTO) {
        String err;

        if (!contractDTO.getProductCat().equals("ISS") && !contractDTO.getProductCat().equals("ACQ")) {
            err = String.format("[%s] is invalid, only Product Category ISS or ACQ is valid", contractDTO.getProductCat());
            throw new InvalidReqBodyException(err);
        }

        if (!contractDTO.getState().equals("A") && !contractDTO.getProductCat().equals("I")) {
            err = String.format("[%s] is invalid, only status A or I is valid", contractDTO.getState());
            throw new InvalidReqBodyException(err);
        }

        if (!contractDTO.getContractCat().equals("DEVICE") && !contractDTO.getContractCat().equals("CARD")) {
            err = String.format("[%s] is invalid, only contract DEVICE or CARD is valid", contractDTO.getContractCat());
            throw new InvalidReqBodyException(err);
        }

    };

}
