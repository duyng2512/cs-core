package com.dng.cs.core.controller;

import com.dng.cs.core.api.PlasticApi;
import com.dng.cs.core.controller.response.PlasticApiResponse;
import com.dng.cs.core.model.ContractContext;
import com.dng.cs.core.model.ModelApiResponse;
import com.dng.cs.core.service.PlasticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlasticController implements PlasticApi {

    private final PlasticService plasticService;

    public PlasticController(PlasticService plasticService) {
        this.plasticService = plasticService;
    }

    @Override
    @RequestMapping(value = "/plastic/lock",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<ModelApiResponse> lockPlastic(ContractContext contractContext) {
        plasticService.lockCard(contractContext.getIdentification(), "01");
        return ResponseEntity.ok(PlasticApiResponse.lockCard(contractContext.getIdentification()));
    }
}
