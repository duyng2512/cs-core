package com.dng.cs.core.config;

import com.dng.cs.core.entity.ClientEntity;
import com.dng.cs.core.model.Client;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.joda.time.DateTime;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Instant;
import java.time.OffsetDateTime;

@Configuration
public class BeanFactoryConfiguration {

    @Bean(name = "clientModelMapper")
    public ModelMapper clientModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Client, ClientEntity> typeMap = modelMapper.createTypeMap(Client.class, ClientEntity.class);

        Converter<OffsetDateTime, Instant> dateConverter = mappingContext -> mappingContext.getSource().toInstant();

        typeMap.addMappings(mapper -> mapper.map(Client::getEmail, ClientEntity::setEMail));
        typeMap.addMappings(map -> map.using(dateConverter).map(Client::getDateCreated, ClientEntity::setDateCreated));
        //typeMap.addMappings(map -> map.using(dateConverter).map(Client::getAddDate, ClientEntity::setAddDate));
        return modelMapper;
    }

    @Bean(name = "contractModelMapper")
    public ModelMapper contractModelMapper(){
        return new ModelMapper();
    }

    @Bean(name = "addressModelMapper")
    public ModelMapper addressModelMapper(){
        return new ModelMapper();
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                    .registerModule(new JavaTimeModule());
    }
}
