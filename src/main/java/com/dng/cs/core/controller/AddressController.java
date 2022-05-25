package com.dng.cs.core.controller;

import com.dng.cs.core.api.AddressApi;
import com.dng.cs.core.model.Address;
import com.dng.cs.core.model.ModelApiResponse;
import com.dng.cs.core.service.AddressService;
import com.dng.cs.core.util.response.AddressApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController implements AddressApi {

    private final AddressService addressService;
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    @PostMapping
    public ResponseEntity<ModelApiResponse> createAddress(Address body) {
        Long newId = addressService.createAddress(body);
        return ResponseEntity.ok(AddressApiResponse.createSuccessful(newId));
    }

    @Override
    @PostMapping("/createWithArray")
    public ResponseEntity<ModelApiResponse> createAddressWithArrayInput(List<Address> body) {
        List<Long> newIdList = addressService.createAddressArray(body);
        return ResponseEntity.ok(AddressApiResponse.createArrayAddressSuccessful(newIdList.toArray()));
    }

    @Override
    @DeleteMapping("/{addressId}")
    public ResponseEntity<ModelApiResponse> deleteAddressByClientId(@PathVariable String addressId) {
        addressService.deleteAddressByClientId(addressId);
        return ResponseEntity.ok(AddressApiResponse.deleteAddress(addressId));
    }

    @Override
    @GetMapping("/client/{clientId}")
    public ResponseEntity<ModelApiResponse> getAddressByClientId(@PathVariable String clientId) {
        List<Address> addressList = addressService.getAddressByClientId(clientId);
        return ResponseEntity.ok(AddressApiResponse.getAddressByClientId(clientId, addressList));
    }

    @Override
    @GetMapping("/{addressId}")
    public ResponseEntity<ModelApiResponse> getAddressByAddressId(@PathVariable String addressId) {
        Address address = addressService.getAddressByAddressId(addressId);
        return ResponseEntity.ok(AddressApiResponse.getAddressByAddressId(addressId, address));
    }

    @Override
    @PutMapping("/{addressId}")
    public ResponseEntity<ModelApiResponse> updateAddress(@PathVariable String addressId, Address body) {
        addressService.updateAddress(addressId, body);
        return ResponseEntity.ok(AddressApiResponse.updateAddress(addressId));
    }
}