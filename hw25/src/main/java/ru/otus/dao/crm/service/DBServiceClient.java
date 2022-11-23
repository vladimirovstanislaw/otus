package ru.otus.dao.crm.service;


import ru.otus.dao.crm.model.Client;

import java.util.List;
import java.util.Optional;

public interface DBServiceClient {

    Client saveClient(Client client);

    Optional<Client> getClient(long id);

    Optional<Client> getClientByLogin(String login);

    List<Client> findAll();
}
