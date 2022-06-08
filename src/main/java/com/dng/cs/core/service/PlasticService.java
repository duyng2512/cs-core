package com.dng.cs.core.service;

import com.dng.cs.core.entity.ContractEntity;
import com.dng.cs.core.entity.PlasticEntity;
import com.dng.cs.core.exception.EntityNotFoundException;
import com.dng.cs.core.exception.InvalidReqBodyException;
import com.dng.cs.core.model.Contract;
import com.dng.cs.core.repository.base.ContractBaseRepository;
import com.dng.cs.core.repository.base.PlasticBaseRepository;
import com.dng.cs.core.service.validate.ContractValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Optional;

@Service
public class PlasticService {
    private final PlasticBaseRepository plasticBaseRepository;
    private final ContractBaseRepository contractBaseRepository;
    private final HashMap<String, String> cacheContractStatus;

    public PlasticService(PlasticBaseRepository plasticBaseRepository,
                          ContractBaseRepository contractBaseRepository,
                          @Qualifier("cacheContractStatus") HashMap<String, String> cacheContractStatus) {
        this.plasticBaseRepository = plasticBaseRepository;
        this.contractBaseRepository = contractBaseRepository;
        this.cacheContractStatus = cacheContractStatus;
    }

    @Transactional
    public void lockCard(String cardNumber, String cardStatus) {
        String err;
        Optional<PlasticEntity> optEntity = plasticBaseRepository.findFirstByCardNumber(cardNumber);
        if (optEntity.isEmpty()) {
            err = String.format("Contract [%s] not found", cardNumber);
            throw new EntityNotFoundException(err);
        }
        if (cacheContractStatus.get(cardStatus) == null) {
            err = String.format("Card Status [%s] invalid", cardStatus);
            throw new InvalidReqBodyException(err);
        }
        PlasticEntity plasticEntity = optEntity.get();
        plasticEntity.setProductionEvent("LOCKED CARD");
        contractBaseRepository.lockContract(plasticEntity.getContractId().getId(), cardStatus);
        plasticBaseRepository.save(plasticEntity);
    }

}
