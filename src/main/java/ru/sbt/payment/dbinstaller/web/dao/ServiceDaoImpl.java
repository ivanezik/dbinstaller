package ru.sbt.payment.dbinstaller.web.dao;

import liquibase.Liquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

@Component
public class ServiceDaoImpl implements ServiceDAO {
     private static final Logger LOGGER = LoggerFactory.getLogger( ServiceDAO.class );

     @Resource(mappedName="jdbc/DSTest")
     private DataSource dataSource;

     private Liquibase liquibase;
     @PostConstruct
     void init() {
         LOGGER.info( "{} init ",this );
     }
}
