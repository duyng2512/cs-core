package com.dng.cs.core.service;

import com.dng.cs.core.entity.AddressEntity;
import com.dng.cs.core.exception.InvalidReqBodyException;
import com.dng.cs.core.model.Address;
import com.dng.cs.core.repository.base.AddressBaseRepository;
import com.dng.cs.core.service.validate.AddressValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class AddressService {

    private final AddressValidator addressValidator;
    private final AddressBaseRepository addressBaseRepository;
    private final ModelMapper modelMapper;

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
                            .map(AddressEntity::getId).collect(Collectors.toList());
    }

    @Transactional
    public void deleteAddressByClientId(String addressId) {
        addressBaseRepository.deleteById(Long.valueOf(addressId));
    }

    @Transactional(readOnly = true)
    public List<Address> getAddressByClientId(String clientId){
        return StreamSupport.stream(addressBaseRepository.find (entityList).spliterator(), true)
                            .map(AddressEntity::getId).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Address getAddressByAddressId(String addressId){}
}
