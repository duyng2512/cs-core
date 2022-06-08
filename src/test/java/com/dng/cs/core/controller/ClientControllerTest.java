package com.dng.cs.core.controller;

import com.dng.cs.core.model.Client;
import com.dng.cs.core.repository.base.ClientBaseRepository;
import com.dng.cs.core.util.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.OffsetDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
class ClientControllerTest {

    ObjectMapper mapper = JsonMapper.builder()
                                    .addModule(new JavaTimeModule())
                                    .build();
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientBaseRepository clientBaseRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addClient_validRequest_returnNewId() throws Exception {

        Client client = new Client();
        client.setClientName("Duy Nguyen Tr");
        client.setRegNumber(TestUtil.randomStringNumber(9));
        client.setPhone(TestUtil.randomStringNumber(10));
        client.setClientCat("PRIVATE");
        client.setEmail("test@gmail.com");
        client.setProductCat("ISS");
        client.setIsReady("Y");
        client.setState("ACTIVE");
        client.setBranch("HCM");

        String bodyReq = mapper.writeValueAsString(client);
        System.out.println(bodyReq);

        MockHttpServletRequestBuilder request = post("/client").content(bodyReq)
                                                                         .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                                  .andDo(print())
                                  .andExpect(status().isOk())
                                  .andReturn();
        String content = result.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);
        long newId = obj.getJSONObject("responseData").getLong("newId");
        System.out.println("newId " + newId);
    }

    @Test
    void deleteClient() {
    }

    @Test
    void getClientById() {
    }

    @Test
    void getClientsByCategory() {
    }

    @Test
    void updateClient() {
    }
}