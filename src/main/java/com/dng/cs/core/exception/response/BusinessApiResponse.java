package com.dng.cs.core.exception.response;

import com.dng.cs.core.exception.EntityNotFoundException;
import com.dng.cs.core.model.ModelApiResponse;
import org.joda.time.DateTime;

public class BusinessApiResponse {

    static private String getCurrentTime(){
        return DateTime.now().toDateTime().toString();
    }

    static public ModelApiResponse exception(String message) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(500L);
        response.setMessage(message);
        return response;
    }

}

