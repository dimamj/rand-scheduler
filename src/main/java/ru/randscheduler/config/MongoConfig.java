package ru.randscheduler.config;

import com.mongodb.MongoClient;
import lombok.Data;
import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.converters.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ru.randscheduler.data.mongo_convertors.MapToSetConverter;
import ru.randscheduler.data.user_data.UserData;

/**
 * Created by dimaMJ on 30.01.2018
 */
@Configuration
public class MongoConfig {

    private static final Morphia morphia = new Morphia();

    static {
        morphia.mapPackageFromClass(UserData.class);
    }

    @Bean
    public AdvancedDatastore dataStore(MongoDbProperties pr) {
        MongoClient client = new MongoClient(pr.host, pr.port);
        Datastore datastore = morphia.createDatastore(client, "rand_scheduler");

        Converters converters = morphia.getMapper().getConverters();
        MapToSetConverter mapToSetConverter = new MapToSetConverter(datastore);
        converters.addConverter(mapToSetConverter);
        mapToSetConverter.init();

        return (AdvancedDatastore) datastore;
    }


    @Component
    @ConfigurationProperties("mongodb")
    @Data
    public static class MongoDbProperties {
        private String host;
        private int port;
    }


}
