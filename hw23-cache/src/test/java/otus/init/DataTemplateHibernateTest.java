package otus.init;

import junit.framework.AssertionFailedError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cachehw.HWCacheDemo;
import ru.otus.cachehw.HwListener;
import ru.otus.cachehw.MyCache;
import ru.otus.core.repository.DataTemplateHibernate;
import ru.otus.crm.model.Address;
import ru.otus.crm.model.Client;
import ru.otus.crm.model.Phone;
import ru.otus.crm.service.DbServiceClientImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class DataTemplateHibernateTest extends AbstractHibernateTest {

    private static final Logger logger = LoggerFactory.getLogger(DataTemplateHibernateTest.class);

    //тест из пред. задания для проверки работоспособности
    @Test
    @DisplayName("Корректно сохраняет, изменяет и загружает клиента по заданному id")
    void shouldSaveAndFindCorrectClientById() {

        var client = new Client(null, "Vasya", new Address(null, "AnyStreet"), List.of(new Phone(null, "13-555-22"),
                new Phone(null, "14-666-333")));


        //when
        var savedClient = transactionManager.doInTransaction(session -> {
            clientTemplate.insert(session, client);
            return client;
        });

        //then
        assertThat(savedClient.getId()).isNotNull();
        assertThat(savedClient.getName()).isEqualTo(client.getName());

        //when
        var loadedSavedClient = transactionManager.doInReadOnlyTransaction(session -> {
                    var res = clientTemplate.findById(session, savedClient.getId())
                            .orElseThrow(() -> new AssertionFailedError("expected: not <null>"));
                    return Optional.ofNullable(res.clone());
                }
        );


        //when
        var updatedClient = savedClient.clone();
        updatedClient.setName("updatedName");
        transactionManager.doInTransaction(session -> {
            clientTemplate.update(session, updatedClient);
            return null;
        });

        //then
        var loadedClient = transactionManager.doInReadOnlyTransaction(session -> {
                    var res = clientTemplate.findById(session, updatedClient.getId())
                            .orElseThrow(() -> new AssertionFailedError("expected: not <null>"));

                    return Optional.of(res.clone());
                }
        );

        //when
        var clientList = transactionManager.doInReadOnlyTransaction(session ->
                clientTemplate.findAll(session).stream()
                        .map(Client::clone).collect(Collectors.toList())
        );

        //then
        assertThat(clientList.size()).isEqualTo(1);
        assertThat(clientList.get(0))
                .usingRecursiveComparison()
                .isEqualTo(updatedClient);


        //when
        clientList = transactionManager.doInReadOnlyTransaction(session ->
                clientTemplate.findByEntityField(session, "name", "updatedName")
                        .stream().map(Client::clone).collect(Collectors.toList())
        );

        //then
        assertThat(clientList.size()).isEqualTo(1);
        assertThat(clientList.get(0))
                .usingRecursiveComparison()
                .isEqualTo(updatedClient);
    }

    @Test
    @DisplayName("Убедится, что наш кеш работает быстрее, чем походы в БД.")
    void cacheFasterDB2() {
        var clientTemplate = new DataTemplateHibernate<>(Client.class);

        var dbServiceClient = new DbServiceClientImpl(transactionManager, clientTemplate);
        long countOfClients = 10_000;

        HwListener<String, Client> listener = new HwListener<String, Client>() {
            @Override
            public void notify(String key, Client value, String action) {
                if (value.getId() == 100) {
                    logger.info("Arrived 100th client");
                }
            }
        };

        dbServiceClient.addListener(listener);
        //Тест запускается с 2-мя настройками
        // 1. -Xmx512m --Xmx512m -Xlog:gc=debug //и выше
        //          При таких настройках кеш при кол-ве клиентов 10_000 остается "жив" до конца теста:
        //cache.size():10000
        //Read 5000 records from DB:39868
        //clientsFromCache.size():5000
        //Read 5000 records from cache:5

        // 2. Размер heap менее 512 -
        //          При таких настройках кеш при кол-ве клиентов 10_000 не останется "жив" до конца теста(256m):
        //cache.size():2229
        //Read 5000 records from DB:40382
        //clientsFromCache.size():5000
        //Read 5000 records from cache:40643


        //заполняем БД и кеш
        for (int idx = 0; idx < countOfClients; idx++) {
            var client = new Client(null, "Vasya" + idx, new Address(null, "AnyStreet" + idx), List.of(new Phone(null, "13-555-22-" + idx),
                    new Phone(null, "14-666-333-" + idx)));
            dbServiceClient.saveClient(client);
        }

        //Вытаскиваем клиентов из БД
        var beforeGettingFromDB = new Date().getTime();
        List<Client> clientsFromDB = new ArrayList<>();
        for (int idx = 0; idx < countOfClients; idx = idx + 2) {
            clientsFromDB.add(dbServiceClient.getClientWithoutCache(idx + 1).get());
        }
        var millsForDBOperations = new Date().getTime() - beforeGettingFromDB;


        //Вытаскиваем клиентов из кеша
        var beforeGettingFromCache = new Date().getTime();
        logger.error("cache.size():{}", dbServiceClient.cacheSize());
        List<Client> clientsFromCache = new ArrayList<>();
        for (int idx = 0; idx < countOfClients; idx = idx + 2) {
            clientsFromCache.add(dbServiceClient.getClient(idx + 1).get());
        }
        logger.error("Read {} records from DB:{}", countOfClients / 2, millsForDBOperations);
        logger.error("clientsFromCache.size():{}", clientsFromCache.size());
        var millsForCacheOperations = new Date().getTime() - beforeGettingFromCache;
        logger.error("Read {} records from cache:{}", countOfClients / 2, millsForCacheOperations);

        assertThat(millsForDBOperations).isGreaterThan(millsForCacheOperations);
        assertThat(countOfClients).isGreaterThan(dbServiceClient.cacheSize());
    }
}
