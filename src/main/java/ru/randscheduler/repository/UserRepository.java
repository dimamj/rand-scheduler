package ru.randscheduler.repository;

import com.mongodb.BasicDBObject;
import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.stereotype.Repository;
import ru.randscheduler.data.user_data.SchedulerData;
import ru.randscheduler.data.user_data.UserData;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dimaMJ on 31.01.2018
 */
@Repository
public class UserRepository extends DefaultRepository<String, UserData> {

    public UserRepository(AdvancedDatastore datastore) {
        super(datastore);
    }

    public List<SchedulerData> getSchedulers(String userId) {
        return get(userId).getSchedulers().values().stream()
                .sorted(Comparator.comparing(v -> v.getFilter().getFrom()))
                .collect(Collectors.toList());
    }

    public void removeScheduler(String userId, int id) {
        UpdateOperations<UserData> ops = datastore.createUpdateOperations(UserData.class)
                .disableValidation()
                .removeAll("schedulers", new BasicDBObject("id", id));

        datastore.update(createQuery().field("_id").equal(userId), ops);
    }
}
