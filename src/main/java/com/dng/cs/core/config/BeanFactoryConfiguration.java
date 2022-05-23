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

        TypeMap<ClientEntity, Client> dateCreatedMapper = modelMapper.createTypeMap(ClientEntity.class, Client.class);
        dateCreatedMapper.addMappings(mapper -> mapper.map(ClientEntity::getDateCreated, (client, o) -> {
            client.setDateCreated(DateTime.now());
        }));

        return modelMapper;
    }


}
