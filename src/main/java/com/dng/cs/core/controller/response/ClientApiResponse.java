package com.dng.cs.core.controller.response;

import com.dng.cs.core.model.Client;
import com.dng.cs.core.model.ModelApiResponse;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ClientApiResponse {

    static private String getCurrentTime(){
        return DateTime.now().toDateTime().toString();
    }

    static public ModelApiResponse createSuccessful(Long newId) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage("Client create successfully");
        response.setResponseData(Map.of("newId", newId));
        return response;
    }

    static public ModelApiResponse uploadImage() {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage("upload image successfully");
        return response;
    }

    static public ModelApiResponse deleteClient(String id) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage(String.format("Client Id [%s] delete successfully", id));
        return response;
    }

    static public ModelApiResponse updateClient(String id) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage(String.format("Client Id [%s] update successfully", id));
        return response;
    }

    static public ModelApiResponse getClient(Client client) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage(String.format("Client Id [%d] load successfully", client.getId()));
        response.setResponseData(client);
        return response;
    }

    static public ModelApiResponse getClientByCategory(String category, List<Client> clients) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage(String.format("Clients with category [%s] load successfully", category));
        response.setResponseData(clients);
        return response;
    }

    static public ModelApiResponse getNearestAddress(String clientId, Object address) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage(String.format("Client [%s] nearest address", clientId));
        response.setResponseData(address);
        return response;
    }
}
