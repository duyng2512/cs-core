package com.dng.cs.core.config;

import com.dng.cs.core.entity.ClientEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class BeanFactoryConfigurationTest {

    @Test
    void objectMapper() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyyMMdd")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyyMMdd")));
        javaTimeModule.addDeserializer(Date.class, new DateDeserializers.SqlDateDeserializer());
        mapper.registerModule(javaTimeModule);

        ClientEntity entity = new ClientEntity();
        entity.setAddDate(new Date(System.currentTimeMillis()));
        String json = mapper.writeValueAsString(entity);
        System.out.println(json);
    }
}