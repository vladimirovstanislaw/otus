package ru.otus.crm.service;

import ru.otus.cachehw.HwListener;
import ru.otus.crm.model.Client;

import java.util.List;
import java.util.Optional;

public interface DBServiceClient {

    Client saveClient(Client client);

    Optional<Client> getClient(long id);

    Optional<Client> getClientWithoutCache(long id);

    List<Client> findAll();

    int cacheSize();

    void addListener(HwListener<String, Client> listener);

    void removeListener(HwListener<String, Client> listener);
}
