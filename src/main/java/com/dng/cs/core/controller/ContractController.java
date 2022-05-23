package com.dng.cs.core.controller;

import com.dng.cs.core.api.ContractApi;
import com.dng.cs.core.model.Contract;
import com.dng.cs.core.model.ModelApiResponse;
import com.dng.cs.core.service.ContractService;
import com.dng.cs.core.util.response.ContractApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contract")
public class ContractController implements ContractApi {
    private final ContractService contractService;
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @Override
    @PostMapping
    public ResponseEntity<ModelApiResponse> addContract(Contract body) {
        Long newId = contractService.addContract(body);
        return ResponseEntity.ok(ContractApiResponse.createSuccessful(newId));
    }

    @Override
    @DeleteMapping("/{contractId}")
    public ResponseEntity<ModelApiResponse> deleteContractById(@PathVariable String contractId) {
        contractService.deleteContractById(Long.valueOf(contractId));
        return ResponseEntity.ok(ContractApiResponse.deleteContract(contractId));
    }

    @Override
    @GetMapping("/{contractId}")
    public ResponseEntity<ModelApiResponse> getContractById(@PathVariable String contractId) {
        Contract contract = contractService.getContractById(Long.valueOf(contractId));
        return ResponseEntity.ok(ContractApiResponse.getContract(contract));
    }

    @Override
    @PutMapping("/{contractId}")
    public ResponseEntity<ModelApiResponse> updateContractById(Contract body, @PathVariable String contractId) {
        contractService.updateContractById(body, Long.valueOf(contractId));
        return ResponseEntity.ok(ContractApiResponse.updateContract(contractId));
    }
}
