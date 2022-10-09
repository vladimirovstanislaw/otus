package ru.otus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.crm.model.Client;
import ru.otus.crm.model.Manager;
import ru.otus.homework.HomeWork;
import ru.otus.homework.mapper.EntityClassMetaData;
import ru.otus.homework.mapper.EntityClassMetaDataImpl;
import ru.otus.homework.mapper.EntitySQLMetaData;
import ru.otus.homework.mapper.EntitySQLMetaDataImpl;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class SqlTests {
    private static final Logger log = LoggerFactory.getLogger(HomeWork.class);

    @Test
    @DisplayName("Смотрим на наши SQL запросы")
    void processingTest() throws Exception {


        //given
        EntityClassMetaData<Client> entityClassMetaDataClient = new EntityClassMetaDataImpl<>(Client.class);
        EntitySQLMetaData entitySQLMetaDataClient = new EntitySQLMetaDataImpl(entityClassMetaDataClient);


        EntityClassMetaData<Manager> entityClassMetaDataManager = new EntityClassMetaDataImpl<>(Manager.class);
        EntitySQLMetaData entitySQLMetaDataManager = new EntitySQLMetaDataImpl(entityClassMetaDataManager);


        //when

        //then

        assertThat(entitySQLMetaDataClient.getSelectAllSql()).isEqualTo("select * from Client");
        assertThat(entitySQLMetaDataClient.getSelectByIdSql()).isEqualTo("select * from Client where id  = ?");
        assertThat(entitySQLMetaDataClient.getInsertSql()).isEqualTo("insert into Client(name) values (?)");
        assertThat(entitySQLMetaDataClient.getUpdateSql()).isEqualTo("update Client set name = ? where id = ?");

        log.info(entitySQLMetaDataManager.getSelectAllSql());
        log.info(entitySQLMetaDataManager.getSelectByIdSql());
        log.info(entitySQLMetaDataManager.getInsertSql());
        log.info(entitySQLMetaDataManager.getUpdateSql());
        assertThat(entitySQLMetaDataManager.getSelectAllSql()).isEqualTo("select * from Manager");
        assertThat(entitySQLMetaDataManager.getSelectByIdSql()).isEqualTo("select * from Manager where no  = ?");
        assertThat(entitySQLMetaDataManager.getInsertSql()).isEqualTo("insert into Manager(label,param1) values (?,?)");
        assertThat(entitySQLMetaDataManager.getUpdateSql()).isEqualTo("update Manager set label = ?,param1 = ? where no = ?");
    }
}
