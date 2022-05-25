package com.dng.cs.core.controller;

import com.dng.cs.core.api.ClientApi;
import com.dng.cs.core.model.Client;
import com.dng.cs.core.model.ModelApiResponse;
import com.dng.cs.core.service.ClientService;
import com.dng.cs.core.util.response.ClientApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/client")
@Slf4j
public class ClientController implements ClientApi {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // POST
    @Override
    @PostMapping
    public ResponseEntity<ModelApiResponse> addClient(Client body) {
        Long newId = clientService.addClient(body);
        return ResponseEntity.ok(ClientApiResponse.createSuccessful(newId));
    }

    @Override
    @PostMapping("/{clientId}/uploadImage")
    public ResponseEntity<ModelApiResponse> uploadFile(@PathVariable String clientId, String additionalMetadata, MultipartFile file) {
        return ResponseEntity.ok(ClientApiResponse.uploadImage());
    }

    // DELETE
    @Override
    @DeleteMapping("/{clientId}")
    public ResponseEntity<ModelApiResponse> deleteClient(@PathVariable String clientId) {
        clientService.deleteClient(Long.valueOf(clientId));
        return ResponseEntity.ok(ClientApiResponse.deleteClient(clientId));
    }

    // GET
    @Override
    @GetMapping("/{clientId}")
    public ResponseEntity<ModelApiResponse> getClientById(@PathVariable String clientId) {
        return ResponseEntity.ok(ClientApiResponse.getClient(clientService.getClientById(Long.valueOf(clientId))));
    }

    @Override
    @GetMapping("/findByCategory")
    public ResponseEntity<ModelApiResponse> getClientsByCategory(@RequestParam("category") String category) {
        return ResponseEntity.ok(ClientApiResponse.getClientByCategory(category, clientService.getClientsByCategory(category)));
    }

    // PUT
    @Override
    @PutMapping("/{clientId}")
    public ResponseEntity<ModelApiResponse> updateClient(@PathVariable String clientId, Client body) {
        clientService.updateClient(Long.valueOf(clientId), body);
        return ResponseEntity.ok(ClientApiResponse.updateClient(clientId));
    }

}
