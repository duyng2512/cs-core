package com.dng.cs.core.config;

import com.dng.cs.core.entity.ClientEntity;
import com.dng.cs.core.model.Client;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFactoryConfiguration {

    @Bean(name = "clientModelMapper")
    public ModelMapper clientModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Client, ClientEntity> emailMapper = modelMapper.createTypeMap(Client.class, ClientEntity.class);
        emailMapper.addMappings(mapper -> mapper.map(Client::getEmail, ClientEntity::seteMail));
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


}
