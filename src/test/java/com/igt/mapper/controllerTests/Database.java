package com.igt.mapper.controllerTests;

import com.igt.mapper.Config;
import com.igt.mapper.DatabaseController;

public class Database {


    public static String db = Config.PERSISTENCE_UNITS.OGM_REDIS.name();

    public static void changeDB(){
        if(!db.equals(Config.PERSISTENCE_UNIT_NAME)) {

            switch (db) {
                case "OGM_MYSQL":
                    DatabaseController.changeDB_mysql();
                    break;
                case "OGM_POSTGRESQL":
                    DatabaseController.changeDB_postgres();
                    break;
                case "OGM_MONGODB":
                    DatabaseController.changeDB_mongodb();
                    break;
                case "OGM_NEO4J":
                    DatabaseController.changeDB_neo4j();
                    break;
                case "OGM_INFINISPAN":
                    DatabaseController.changeDB_infinispan();
                    break;
                case "OGM_CASSANDRA":
                    DatabaseController.changeDB_cassandra();
                    break;
                case "OGM_REDIS":
                    DatabaseController.changeDB_redis();
                    break;

            }
        }
    }
}
