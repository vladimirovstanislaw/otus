package ru.otus.demo;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.repository.executor.DbExecutorImpl;
import ru.otus.crm.datasource.DriverManagerDataSource;
import ru.otus.crm.model.Client;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ExecutorDemo {
    private static final String URL = "jdbc:postgresql://localhost:5430/demoDB";
    private static final String USER = "usr";
    private static final String PASSWORD = "pwd";

    private static final Logger log = LoggerFactory.getLogger(ExecutorDemo.class);

    public static void main(String[] args) {
        var dataSource = new DriverManagerDataSource(URL, USER, PASSWORD);
        flywayMigrations(dataSource);
        try (var connection = dataSource.getConnection()) {
            var executor = new DbExecutorImpl();
            var clientid = executor.executeStatement(connection, "insert into client(name) values (?)", Collections.singletonList("testUserName"));
            log.info("created client:{}", clientid);
            connection.commit();

            var client = executor.executeSelect(connection, "select id, name from client where id  = ?", List.of(clientid)
                    , rs -> {
                        try {
                            if (rs.next()) {
                                return new Client(rs.getLong("id"), rs.getString("name"));
                            }
                        } catch (SQLException ex) {
                            log.error(ex.getMessage(), ex);
                        }
                        return null;
                    });
            log.info("client:{}", client);
        } catch (Exception ex) {

        }


    }

    private static void flywayMigrations(DataSource dataSource) {
        log.info("db migration init...");
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/migration")
                .load();
        flyway.migrate();
        log.info("db migration finished.");
        log.info("****");
    }

    public <E, T> List<E> come(List<T> tList) {
        return null;
    }

}
