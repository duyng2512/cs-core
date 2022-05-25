package com.dng.cs.core.service;

import com.dng.cs.core.entity.AddressEntity;
import com.dng.cs.core.entity.ClientEntity;
import com.dng.cs.core.exception.EntityNotFoundException;
import com.dng.cs.core.exception.InvalidReqBodyException;
import com.dng.cs.core.model.Address;
import com.dng.cs.core.repository.base.AddressBaseRepository;
import com.dng.cs.core.service.validate.AddressValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class AddressService {

    private final AddressValidator addressValidator;
    private final AddressBaseRepository addressBaseRepository;
    private final ModelMapper modelMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public AddressService(AddressValidator addressValidator,
                          AddressBaseRepository addressBaseRepository,
                          @Qualifier("addressModelMapper") ModelMapper modelMapper) {
        this.addressValidator = addressValidator;
        this.addressBaseRepository = addressBaseRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Long createAddress(Address addressDTO) {
        addressValidator.validate(addressDTO);
        if (addressDTO.getId()!=null){
            throw new InvalidReqBodyException("Id must be null");
        }
        return addressBaseRepository.save(modelMapper.map(addressDTO, AddressEntity.class))
                                    .getId();
    }

    @Transactional
    public List<Long> createAddressArray(List<Address> addressDtoList) {
        addressDtoList.forEach(address -> {
            addressValidator.validate(address);
            if (address.getId()!=null){
                throw new InvalidReqBodyException("All Id must be null");
            }
        });
        List<AddressEntity> entityList = addressDtoList.stream()
                                                       .map(address -> modelMapper.map(address, AddressEntity.class))
                                                       .collect(Collectors.toList());
        return StreamSupport.stream(addressBaseRepository.saveAll(entityList).spliterator(), true)
                            .map(AddressEntity::getId)
                            .collect(Collectors.toList());
    }

    @Transactional
    public void updateAddress(String addressId, Address addressDTO) {
        String err;
        addressValidator.validate(addressDTO);
        Optional<AddressEntity> optEntity = addressBaseRepository.findById(Long.valueOf(addressId));
        if (optEntity.isEmpty()) {
            err = String.format("Address id [%s] not found", addressId);
            throw new EntityNotFoundException(err);
        } else {
            AddressEntity newEntity = optEntity.get();
            modelMapper.map(addressDTO, newEntity);
            addressBaseRepository.save(newEntity);
        }
    }

    @Transactional
    public void deleteAddressByClientId(String addressId) {
        addressBaseRepository.deleteById(Long.valueOf(addressId));
    }

    @Transactional(readOnly = true)
    public List<Address> getAddressByClientId(String clientId){
        ClientEntity entity = entityManager.getReference(ClientEntity.class, Long.valueOf(clientId));
        List<AddressEntity> listAddress = addressBaseRepository.findAddressEntitiesByClientId(entity);
        return addressBaseRepository.findAddressEntitiesByClientId(entity)
                                    .parallelStream()
                                    .map(m -> modelMapper.map(m, Address.class))
                                    .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Address getAddressByAddressId(String addressId) {
        return modelMapper.map(addressBaseRepository.findById(Long.valueOf(addressId)), Address.class);
    }
}
