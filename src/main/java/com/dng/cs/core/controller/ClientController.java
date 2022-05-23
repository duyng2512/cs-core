package com.dng.cs.core.controller;

import com.dng.cs.core.api.ClientApi;
import com.dng.cs.core.model.Client;
import com.dng.cs.core.model.ModelApiResponse;
import com.dng.cs.core.service.ClientService;
import com.dng.cs.core.util.response.ClientApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Consumes;

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
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelApiResponse> addClient(Client body) {
        Long newId = clientService.addClient(body);
        return ResponseEntity.ok(ClientApiResponse.createSuccessful(newId));
    }

    @Override
    @PostMapping("/{clientId}/uploadImage")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelApiResponse> uploadFile(@PathVariable Long clientId, String additionalMetadata, MultipartFile file) {
        return ResponseEntity.ok(ClientApiResponse.uploadImage());
    }

    // DELETE
    @Override
    @DeleteMapping("/{clientId}")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelApiResponse> deleteClient(@PathVariable Long clientId) {
        clientService.deleteClient(clientId);
        return ResponseEntity.ok(ClientApiResponse.deleteClient(clientId));
    }

    // GET
    @Override
    @GetMapping("/{clientId}")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelApiResponse> getClientById(@PathVariable Long clientId) {
        return ResponseEntity.ok(ClientApiResponse.getClient(clientService.getClientById(clientId)));
    }

    @Override
    @GetMapping("/findByCategory")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelApiResponse> getClientsByCategory(@RequestParam("category") String category) {
        return ResponseEntity.ok(ClientApiResponse.getClientByCategory(category, clientService.getClientsByCategory(category)));
    }

    @Override
    @PutMapping("/{clientId}")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelApiResponse> updateClient(@PathVariable Long clientId, Client body) {
        clientService.updateClient(clientId, body);
        return ResponseEntity.ok(ClientApiResponse.updateClient(clientId));
    }

}
