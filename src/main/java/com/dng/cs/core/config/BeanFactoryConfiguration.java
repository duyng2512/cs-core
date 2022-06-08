package com.dng.cs.core.config;

import com.dng.cs.core.entity.ClientEntity;
import com.dng.cs.core.entity.ContractEntity;
import com.dng.cs.core.model.Client;
import com.dng.cs.core.model.Contract;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.patterns.IScope;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Configuration
@Slf4j
public class BeanFactoryConfiguration {

    @Bean(name = "clientModelMapper")
    public ModelMapper clientModelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        // From DTO to ENTITY
        TypeMap<Client, ClientEntity> dtoToEntity = modelMapper.createTypeMap(Client.class, ClientEntity.class);
        Converter<String, Instant> dateTimeToInstant = map -> Instant.parse(map.getSource());
        dtoToEntity.addMappings(map -> map.using(dateTimeToInstant).map(Client::getDateCreated, ClientEntity::setDateCreated));

        // From ENTITY to DTO
        TypeMap<ClientEntity, Client> entityToDto = modelMapper.createTypeMap(ClientEntity.class, Client.class);
        Converter<String, String>  statusDictionary = map -> map.getSource().equals("A") ? "ACTIVE" : "INACTIVE";
        entityToDto.addMappings(map -> map.using(statusDictionary).map(ClientEntity::getState, Client::setState));

        return modelMapper;
    }

    @Bean(name = "contractModelMapper")
    public ModelMapper contractModelMapper(@Qualifier("cacheContractStatus") HashMap<String, String> cacheContractStatus){
        ModelMapper modelMapper = new ModelMapper();

        // From ENTITY to DTO
        TypeMap<ContractEntity, Contract> entityToDto = modelMapper.createTypeMap(ContractEntity.class, Contract.class);
        Converter<String, String>  statusDictionary = map -> cacheContractStatus.get(map.getSource());
        entityToDto.addMappings(map -> map.using(statusDictionary).map(ContractEntity::getStatus, Contract::setStatus));

        return modelMapper;
    }

    @Bean(name = "addressModelMapper")
    public ModelMapper addressModelMapper(){
        return new ModelMapper();
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(1);
        threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        return threadPoolTaskScheduler;
    }
}
