package com.dng.cs.core.controller.response;

import com.dng.cs.core.model.ModelApiResponse;
import org.joda.time.DateTime;

import java.util.Map;

public class PlasticApiResponse {

    static private String getCurrentTime(){
        return DateTime.now().toDateTime().toString();
    }

    static public ModelApiResponse lockCard(String id) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage(String.format("Contract [%s] lock successfully", id));
        return response;
    }


}
