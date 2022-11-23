package ru.otus.services;

import ru.otus.dao.crm.service.DBServiceClient;

public class UserAuthServiceImpl implements UserAuthService {

    private final DBServiceClient dbServiceClient;

    public UserAuthServiceImpl(DBServiceClient dbServiceClient) {
        this.dbServiceClient = dbServiceClient;
    }

    @Override
    public boolean authenticate(String login, String password) {
        return dbServiceClient.getClientByLogin(login)
                .map(client -> client.getPassword().equals(password))
                .orElse(false);
    }

}
