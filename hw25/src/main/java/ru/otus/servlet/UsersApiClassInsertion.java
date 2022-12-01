package ru.otus.servlet;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.otus.dao.crm.model.Client;
import ru.otus.dao.crm.service.DBServiceClient;

import java.io.IOException;


public class UsersApiClassInsertion extends HttpServlet {


    private static final String PARAM_NAME = "name";
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";


    private final DBServiceClient dbServiceClient;
    private final Gson gson;

    public UsersApiClassInsertion(DBServiceClient dbServiceClient, Gson gson) {
        this.dbServiceClient = dbServiceClient;
        this.gson = gson;
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter(PARAM_NAME);
        String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
        dbServiceClient.saveClient(new Client(name, login, password));
        response.setStatus(200);
    }


}

