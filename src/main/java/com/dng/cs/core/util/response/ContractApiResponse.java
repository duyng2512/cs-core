package com.dng.cs.core.util.response;

import com.dng.cs.core.model.Client;
import com.dng.cs.core.model.Contract;
import com.dng.cs.core.model.ModelApiResponse;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;

@Component
public class ContractApiResponse {

    static private String getCurrentTime(){
        return DateTime.now().toDateTime().toString();
    }

    static public ModelApiResponse createSuccessful(Long newId) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage("Contract create successfully");
        response.setResponseData(Map.of("newId", newId));
        return response;
    }

    static public ModelApiResponse deleteContract(Long id) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage(String.format("Contract Id [%d] delete successfully", id));
        return response;
    }

    static public ModelApiResponse getContract(Contract contract) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage(String.format("Contract Id [%d] load successfully", contract.getId()));
        response.setResponseData(contract);
        return response;
    }

    static public ModelApiResponse updateContract(Long id) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage(String.format("Contract Id [%d] update successfully", id));
        return response;
    }

}
