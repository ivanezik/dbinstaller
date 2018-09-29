package ru.sbt.payment.dbinstaller.web.service;

import liquibase.Liquibase;
import liquibase.changelog.ChangeLogParameters;
import liquibase.database.Database;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.FileSystemResourceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

@Component
public class LiqServiceIml implements LiqService {
    private Logger LOGGER = LoggerFactory.getLogger( LiqService.class );
    private Liquibase liquibase;
    private Database database;
    private final String CHANGE_LOG = "/ru/sbt/payment/dbinstaller/web/sql/changeSet.xml";
    @Autowired
    public LiqServiceIml(Database database) {
        this.database = database;
    }
    @PostConstruct
    void init() {
        LOGGER.info( " databases {}",database );
        liquibase = new Liquibase( CHANGE_LOG,  new ClassLoaderResourceAccessor(getClass().getClassLoader()),database );
        LOGGER.info( "liquibase instance {}",liquibase );
        ChangeLogParameters changeLogParameters = liquibase.getChangeLogParameters();
        try {
            LOGGER.info( liquibase.getDatabaseChangeLog().getPhysicalFilePath());
        } catch (LiquibaseException e) {
            e.printStackTrace();
        }
        //changeLogParameters.getChangeLogParameters().stream().forEach( c-> System.out.println( c.getChangeLog() ) );
     }
}
