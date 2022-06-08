package com.dng.cs.core.controller.response;

import com.dng.cs.core.model.ModelApiResponse;
import org.joda.time.DateTime;

public class AuthApiResponse {

    static private String getCurrentTime(){
        return DateTime.now().toDateTime().toString();
    }

    static public ModelApiResponse login() {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage("Login successfully");
        return response;
    }

    static public ModelApiResponse logout() {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage("Logout successfully");
        return response;
    }

    static public ModelApiResponse signIn() {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(200L);
        response.setMessage("Sign in successfully");
        return response;
    }

    static public ModelApiResponse noPaiKey() {
        ModelApiResponse response = new ModelApiResponse();
        response.setTime(getCurrentTime());
        response.setCode(500L);
        response.setMessage("No api key found in header");
        return response;
    }
}
