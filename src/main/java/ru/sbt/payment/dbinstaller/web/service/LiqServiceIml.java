package ru.sbt.payment.dbinstaller.web.service;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.changelog.ChangeLogParameters;
import liquibase.changelog.ChangeSet;
import liquibase.database.Database;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class LiqServiceIml implements LiqService {
    private Logger LOGGER = LoggerFactory.getLogger( LiqService.class );
    private Liquibase liquibase;
    private Database database;
    private final String CHANGE_LOG = "/ru/sbt/payment/dbinstaller/web/config/changeSet.xml";
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
              List<ChangeSet> changeSetList  = liquibase.getDatabaseChangeLog().getChangeSets();
              changeSetList.stream().forEach( ch -> LOGGER.info( "set id={} author={} change={}",ch.getId(),ch.getAuthor(),ch.getChanges() ) );

                //liquibase.update( new Contexts(  ) );
                //liquibase.getChangeSetStatuses( new Contexts(  ),new LabelExpression(  ) ).stream().forEach( s -> LOGGER.info( " changeSet statuses {} = {} ",s.getChangeSet().getId(),s.getWillRun() ));
               liquibase.rollback( "1.0.1",new Contexts(  ) );

        } catch (LiquibaseException e) {
            e.printStackTrace();
        }
        //changeLogParameters.getChangeLogParameters().stream().forEach( c-> System.out.println( c.getChangeLog() ) );
     }
}
