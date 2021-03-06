package com.dng.cs.core.service;


import com.dng.cs.core.entity.AddressEntity;
import com.dng.cs.core.entity.ClientEntity;
import com.dng.cs.core.exception.BusinessConstraintException;
import com.dng.cs.core.exception.EntityNotFoundException;
import com.dng.cs.core.exception.InvalidReqBodyException;
import com.dng.cs.core.model.Address;
import com.dng.cs.core.model.Client;
import com.dng.cs.core.repository.base.ClientBaseRepository;
import com.dng.cs.core.service.validate.ClientValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;



import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientBaseRepository clientBaseRepository;
    private final ClientValidator clientValidator;
    private final ModelMapper modelMapper;

    public ClientService(ClientBaseRepository clientBaseRepository,
                         ClientValidator clientValidator,
                         @Qualifier("clientModelMapper") ModelMapper modelMapper) {
        this.clientBaseRepository = clientBaseRepository;
        this.clientValidator = clientValidator;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Long addClient(Client clientDTO) {
        clientValidator.validate(clientDTO);
        String err;
        if (clientDTO.getId() != null) {
            err = "Id must be null";
            throw new InvalidReqBodyException(err);
        }
        if (clientBaseRepository.existsClientEntityByRegNumber(clientDTO.getRegNumber())){
            err = String.format("Reg number %s already existed", clientDTO.getRegNumber());
            throw new BusinessConstraintException(err);
        }
        ClientEntity entity = modelMapper.map(clientDTO, ClientEntity.class);
        ClientEntity savedEntity = clientBaseRepository.save(entity);
        return savedEntity.getId();
    }

    @Transactional
    public void deleteClient(Long clientId) {
        checkingIfExist(clientId);
        clientBaseRepository.deleteById(clientId);
    }

    @Transactional(readOnly = true)
    public Client getClientById(Long clientId) {
        ClientEntity entity = checkingIfExist(clientId);
        return modelMapper.map(entity, Client.class);
    }

    @Transactional(readOnly = true)
    public Address getNearestAddress(Long clientId) {
        ClientEntity entity = checkingIfExist(clientId);

        List<AddressEntity> listAddress = entity.getAddresses();
        if (listAddress.isEmpty()) {
            throw new EntityNotFoundException("There is none address for this client");
        }
        AddressEntity addressEntity = listAddress.get(0);
        for (int i = 1; i < listAddress.size(); i++){
            if (listAddress.get(i).getAddressLine().length() > addressEntity.getAddressLine().length()){
                addressEntity = listAddress.get(i);
            }
        }
        return modelMapper.map(addressEntity, Address.class);
    }

    @Transactional(readOnly = true)
    public List<Client> getClientsByCategory(String category) {
        String err;
        if (!category.equals("PRIVATE") && !category.equals("COMMERCIAL")) {
            err = String.format("Invalid [%s], Only Client Category PRIVATE or COMMERCIAL is valid", category);
            throw new InvalidReqBodyException(err);
        }
        return clientBaseRepository.findClientEntitiesByClientCat(category)
                .stream()
                .map(s -> modelMapper.map(s, Client.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateClient(@PathVariable Long clientId, Client clientDTO) {
        clientValidator.validate(clientDTO);

        String err;
        Optional<ClientEntity> optEntity = clientBaseRepository.findById(clientId);

        if (optEntity.isEmpty()){
            err = String.format("Client ID %d not found", clientId);
            throw new EntityNotFoundException(err);
        } else {
            ClientEntity entity = optEntity.get();
            modelMapper.map(clientDTO, entity);
            clientBaseRepository.save(entity);
        }
    }

    private ClientEntity checkingIfExist(Long clientId) {
        String err;
        Optional<ClientEntity> optEntity = clientBaseRepository.findById(clientId);
        if (optEntity.isEmpty()) {
            err = String.format("Client ID %d not found", clientId);
            throw new EntityNotFoundException(err);
        }
        return optEntity.get();
    }
}
