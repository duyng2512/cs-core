package com.dng.cs.core.controller;

import com.dng.cs.core.model.Address;
import com.dng.cs.core.repository.base.AddressBaseRepository;
import com.dng.cs.core.service.AddressService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
     @ SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
     @ AutoConfigureMockMvc
     -> Load the mock test without starting the server, load whole configuration

    @ WebMvcTest(AddressController.class): narrow down to web layers

    Using this annotation will disable full auto-configuration and instead apply only configuration relevant to MVC tests (i.e. @Controller, @ControllerAdvice,
    @ JsonComponent, Converter/GenericConverter, Filter, WebMvcConfigurer and HandlerMethodArgumentResolver beans but not @Component, @Service or @Repository beans

 */

@WebMvcTest(AddressController.class)
@TestPropertySource(locations="classpath:application-test.properties")
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;


   @MockBean
   private AddressService service;

   @MockBean
   private AddressBaseRepository repository;


    @Test
    void getAddressByClientId_validId_returnClientDTO() throws Exception {
        this.mockMvc.perform(get("/address/100"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value("200"));
                    //.andExpect(jsonPath("$.responseData.id").value("100"));
    }

    @Test
    void updateAddress() throws Exception {
        Address address = new Address();
        address.setId(301L);
        address.setEmail("duyng@gmail.com");
        address.setPhone("0909399299");
        address.setZipcode("700000");
        address.setAddressLine("72 Le Thanh Ton");
        Gson gson = new Gson();
        String json = gson.toJson(address);

        this.mockMvc.perform(put("/address/301").content(json)
                                                           .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value("200"));
    }
}