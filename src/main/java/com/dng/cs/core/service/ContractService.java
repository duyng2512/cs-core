package com.dng.cs.core.service;

import com.dng.cs.core.entity.ContractEntity;
import com.dng.cs.core.exception.EntityNotFoundException;
import com.dng.cs.core.exception.InvalidReqBodyException;
import com.dng.cs.core.model.Contract;
import com.dng.cs.core.repository.base.ContractBaseRepository;
import com.dng.cs.core.service.validate.ContractValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ContractService {

    private final ContractBaseRepository contractBaseRepository;
    private final ContractValidator contractValidator;
    private final ModelMapper modelMapper;

    public ContractService(ContractBaseRepository contractBaseRepository,
                           ContractValidator contractValidator,
                           ModelMapper modelMapper) {
        this.contractBaseRepository = contractBaseRepository;
        this.contractValidator = contractValidator;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Long addContract(Contract contractDTO) {
        contractValidator.validate(contractDTO);
        if (contractDTO.getId() != null) {
            throw new InvalidReqBodyException("Contract id must be null");
        }
        ContractEntity entity = contractBaseRepository.save(modelMapper.map(contractDTO, ContractEntity.class));
        return entity.getId();
    }

    @Transactional
    public void deleteContractById(Long contractId) {
        contractBaseRepository.deleteById(contractId);
    }

    @Transactional
    public void updateContractById(Contract contractDTO, @PathVariable Long contractId) {
        String err;
        contractValidator.validate(contractDTO);
        if (!contractBaseRepository.existsById(contractId)) {
            err = String.format("Contract id [%d] not found", contractId);
            throw new EntityNotFoundException(err);
        }
        contractBaseRepository.save(modelMapper.map(contractDTO, ContractEntity.class));
    }


    @Transactional(readOnly = true)
    public Contract getContractById(Long contractId) {
        return modelMapper.map(contractBaseRepository.findById(contractId), Contract.class);
    }


}
