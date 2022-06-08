package com.dng.cs.core.controller.response;

import com.dng.cs.core.model.Address;
import com.dng.cs.core.model.ModelApiResponse;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AddressApiResponse {

    static private String getCurrentTime(){
        return DateTime.now().toDateTime().toString();
    }

    static public ModelApiResponse createSuccessful(Long newId) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage("Address create successfully");
        response.setResponseData(Map.of("newId", newId));
        return response;
    }

    static public ModelApiResponse createArrayAddressSuccessful(Object[] newIdList) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage("Addresses create successfully");
        response.setResponseData(Map.of("newId", newIdList));
        return response;
    }

    static public ModelApiResponse deleteAddress(String id) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage(String.format("Address Id [%s] delete successfully", id));
        return response;
    }

    static public ModelApiResponse getAddressByClientId(String id, List<Address> addressList) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage(String.format("Address of Client [%s] load successfully", id));
        response.setResponseData(addressList);
        return response;
    }

    static public ModelApiResponse getAddressByAddressId(String id, Address address) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage(String.format("Address id [%s] load successfully", id));
        response.setResponseData(address);
        return response;
    }

    static public ModelApiResponse updateAddress(String id) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage(String.format("Address id [%s] update successfully", id));
        return response;
    }
}
