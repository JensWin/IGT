package com.igt.mapper;

public class Config {

    public static String PERSISTENCE_UNIT_NAME = PERSISTENCE_UNITS.OGM_INFINISPAN.name();
    public static final Integer TRANSACTION_TIMEOUT = 900000;
    public enum PERSISTENCE_UNITS {
        OGM_MYSQL, OGM_POSTGRESQL, OGM_MONGODB, OGM_NEO4J, OGM_INFINISPAN, OGM_CASSANDRA, OGM_REDIS,
    }
}
