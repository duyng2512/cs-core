package com.dng.cs.core.service;

import com.dng.cs.core.model.Address;
import com.dng.cs.core.repository.base.AddressBaseRepository;
import com.dng.cs.core.service.validate.AddressValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {

    private final AddressValidator addressValidator;
    private final AddressBaseRepository addressBaseRepository;

    public AddressService(AddressValidator addressValidator, AddressBaseRepository addressBaseRepository) {
        this.addressValidator = addressValidator;
        this.addressBaseRepository = addressBaseRepository;
    }

    @Transactional
    public Long createAddress(Address addressDTO) {
        return 0L;
    }

}
