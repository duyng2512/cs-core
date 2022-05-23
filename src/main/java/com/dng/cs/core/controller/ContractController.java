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
    public ResponseEntity<ModelApiResponse> deleteContractById(@PathVariable Long contractId) {
        contractService.deleteContractById(contractId);
        return ResponseEntity.ok(ContractApiResponse.deleteContract(contractId));
    }

    @Override
    @GetMapping("/{contractId}")
    public ResponseEntity<ModelApiResponse> getContractById(@PathVariable Long contractId) {
        Contract contract = contractService.getContractById(contractId);
        return ResponseEntity.ok(ContractApiResponse.getContract(contract));
    }

    @Override
    @PutMapping("/{contractId}")
    public ResponseEntity<ModelApiResponse> updateContractById(Contract body, @PathVariable Long contractId) {
        contractService.updateContractById(body, contractId);
        return ResponseEntity.ok(ContractApiResponse.updateContract(contractId));
    }
}
