package ru.otus.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.PersistenceCreator;

@Table(name = "client")
public class Client implements Cloneable {

    @Id
    private Long id;

    private String name;


    private String login;


    private String password;

    public Client() {
    }

    public Client(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    @PersistenceCreator
    public Client(Long id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
