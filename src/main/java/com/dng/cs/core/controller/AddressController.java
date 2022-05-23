package com.dng.cs.core.controller;

import com.dng.cs.core.api.AddressApi;
import com.dng.cs.core.model.Address;
import com.dng.cs.core.model.ModelApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController implements AddressApi {

    @Override
    @PostMapping
    public ResponseEntity<ModelApiResponse> createAddress(Address body) {
        return null;
    }

    @Override
    @PostMapping("/createWithArray")
    public ResponseEntity<ModelApiResponse> createAddressWithArrayInput(List<Address> body) {
        return null;
    }

    @Override
    @DeleteMapping("/{addressId}")
    public ResponseEntity<ModelApiResponse> deleteAddressByClientId(@PathVariable String addressId) {
        return null;
    }

    @Override
    @GetMapping("/{clientId}")
    public ResponseEntity<ModelApiResponse> getAddressByClientId(@PathVariable String clientId) {
        return null;
    }

    @Override
    @GetMapping("/{addressId}")
    public ResponseEntity<ModelApiResponse> getAddressByAddressId(@PathVariable String addressId) {
        return null;
    }

    @Override
    @PutMapping("/{addressId}")
    public ResponseEntity<ModelApiResponse> updateAddress(@PathVariable String addressId, Address body) {
        return null;
    }
}