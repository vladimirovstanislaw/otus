package ru.otus.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.model.Client;
import ru.otus.model.ClientDTO;
import ru.otus.service.ClientService;

import java.util.List;

@CrossOrigin("*")
@RestController
public class ClientRestController {
    @Autowired
    private final ClientService service;
//done
    public ClientRestController(ClientService service) {
        this.service = service;
    }

    @GetMapping(value = "/api/clients/all")
    public List<Client> getAllApiUsers() {
        return service.findAll();
    }

    @PostMapping(value = "/api/client")
    public ResponseEntity<Client> addApiUser(@RequestBody ClientDTO clientDTO) {
        if (clientDTO != null) {
            Client client = new Client();
            client.setPassword(clientDTO.getPassword());
            client.setLogin(clientDTO.getLogin());
            client.setName(clientDTO.getName());
            service.saveClient(client);
            return ResponseEntity.ok(client);
        }
        return ResponseEntity.badRequest().build();
    }

}
