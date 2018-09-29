package ru.sbt.payment.dbinstaller.web.config;

import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.core.PostgresDatabase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ComponentScan({ "ru.sbt.payment" })
public class LiquibaseConfig {

    @Resource(mappedName = "jdbc/DSTest")
    private DataSource dataSource;

    @Bean
    public Database getDatabase() {
        try {
            return DatabaseFactory.getInstance().findCorrectDatabaseImplementation( new JdbcConnection( dataSource.getConnection() ) );
        } catch (DatabaseException  | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
