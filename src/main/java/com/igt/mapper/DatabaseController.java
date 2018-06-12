package com.igt.mapper;

import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;


@Controller
@RequestMapping("/db")
public class DatabaseController {
    public static TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);

    @RequestMapping(value="/mysql", method = RequestMethod.GET)
    public static @ResponseBody String changeDB_mysql(){
        close();
        Config.PERSISTENCE_UNIT_NAME = Config.PERSISTENCE_UNITS.OGM_MYSQL.name();
        emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);
        return "Database change to MySQL";
    }
    @RequestMapping(value="/postgres", method = RequestMethod.GET)
    public static @ResponseBody String changeDB_postgres(){
        close();
        Config.PERSISTENCE_UNIT_NAME = Config.PERSISTENCE_UNITS.OGM_POSTGRESQL.name();
        emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);
        return "Database change to Postgres";
    }
    @RequestMapping(value="/cassandra", method = RequestMethod.GET)
    public static @ResponseBody String changeDB_cassandra(){
        close();
        Config.PERSISTENCE_UNIT_NAME = Config.PERSISTENCE_UNITS.OGM_CASSANDRA.name();
        emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);
        return "Database change to Cassandra";
    }
    @RequestMapping(value="/mongodb", method = RequestMethod.GET)
    public static @ResponseBody String changeDB_mongodb(){
        close();
        Config.PERSISTENCE_UNIT_NAME = Config.PERSISTENCE_UNITS.OGM_MONGODB.name();
        emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);
        return "Database change to MongoDB";
    }
    @RequestMapping(value="/neo4j", method = RequestMethod.GET)
    public static @ResponseBody String changeDB_neo4j(){
        close();
        Config.PERSISTENCE_UNIT_NAME = Config.PERSISTENCE_UNITS.OGM_NEO4J.name();
        emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);
        return "Database change to Neo4j";
    }
    @RequestMapping(value="/redis", method = RequestMethod.GET)
    public static @ResponseBody String changeDB_redis(){
        close();
        Config.PERSISTENCE_UNIT_NAME = Config.PERSISTENCE_UNITS.OGM_REDIS.name();
        emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);
        return "Database change to Redis";
    }
    @RequestMapping(value="/infinispan", method = RequestMethod.GET)
    public static @ResponseBody String changeDB_infinispan(){
        close();
        Config.PERSISTENCE_UNIT_NAME = Config.PERSISTENCE_UNITS.OGM_INFINISPAN.name();
        emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);
        return "Database change to Infinispan";
    }

    @RequestMapping(value="/close", method = RequestMethod.GET)
    public static @ResponseBody void changeDB_close(){
        emf.close();
    }

    public static void close(){
        try {
            emf.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static TransactionManager getTm(){
        tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
        return tm;
    }
}
