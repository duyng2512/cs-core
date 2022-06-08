package com.dng.cs.core.controller;

import com.dng.cs.core.api.AuthApi;
import com.dng.cs.core.api.PlasticApi;
import com.dng.cs.core.controller.response.AuthApiResponse;
import com.dng.cs.core.controller.response.PlasticApiResponse;
import com.dng.cs.core.model.ContractContext;
import com.dng.cs.core.model.ModelApiResponse;
import com.dng.cs.core.service.PlasticService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {

    @ApiOperation(value = "Login User", nickname = "loginUser", notes = "", response = ModelApiResponse.class, tags={ "Auth", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ModelApiResponse.class),
            @ApiResponse(code = 400, message = "Invalid username/password supplied") })
    @RequestMapping(value = "/auth/login",
            produces = { "application/json", "application/xml" },
            method = RequestMethod.POST)
    public ResponseEntity<ModelApiResponse> loginUser(HttpServletRequest request) {
        return ResponseEntity.ok(AuthApiResponse.login());
    }

    @ApiOperation(value = "Logs out current logged in user session", nickname = "logoutUser", notes = "", response = ModelApiResponse.class, tags={ "Auth", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ModelApiResponse.class) })
    @RequestMapping(value = "/auth/logout",
            produces = { "application/json", "application/xml" },
            method = RequestMethod.POST)
    public ResponseEntity<ModelApiResponse> logoutUser(HttpServletRequest request) {
        return ResponseEntity.ok(AuthApiResponse.logout());
    }

    @ApiOperation(value = "signInUser", nickname = "signInUser", notes = "This can only be done by the logged in user.", response = ModelApiResponse.class, tags={ "Auth", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ModelApiResponse.class) })
    @RequestMapping(value = "/auth/signin",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<ModelApiResponse> signInUser(HttpServletRequest request) {
        return ResponseEntity.ok(AuthApiResponse.signIn());
    }
}
