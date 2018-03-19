package ru.randscheduler.repository;

import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.randscheduler.data.user_data.UserData;

/**
 * Created by dimaMJ on 31.01.2018
 */
@Repository
public class UserRepository extends DefaultRepository<String, UserData> {

    private static final String cl = "users_data";

    public UserRepository(AdvancedDatastore datastore) {
        super(datastore);
    }
}
