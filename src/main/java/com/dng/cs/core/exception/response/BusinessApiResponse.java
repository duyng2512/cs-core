package com.dng.cs.core.exception.response;

import com.dng.cs.core.model.ModelApiResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.DateTime;

import java.sql.SQLException;

public class BusinessApiResponse {

    static private String getCurrentTime(){
        return DateTime.now().toDateTime().toString();
    }

    static public ModelApiResponse businessException(String message) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(500L);
        response.setMessage(message);
        return response;
    }

    static public ModelApiResponse sqlException(SQLException exception) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode((long) exception.getErrorCode());
        response.setMessage(exception.getCause().getMessage());
        return response;
    }

    static public ModelApiResponse constraintException(ConstraintViolationException exception) {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode((long) exception.getErrorCode());
        response.setMessage(exception.getCause().getMessage());
        return response;
    }
}

