package com.dng.cs.core.config;

import com.dng.cs.core.entity.ClientEntity;
import com.dng.cs.core.model.Client;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BeanFactoryConfiguration {

    @Bean(name = "clientModelMapper")
    public ModelMapper clientModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Client, ClientEntity> emailMapper = modelMapper.createTypeMap(Client.class, ClientEntity.class);
        emailMapper.addMappings(mapper -> mapper.map(Client::getEmail, ClientEntity::setEMail));
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
