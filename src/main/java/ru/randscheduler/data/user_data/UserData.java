package ru.randscheduler.data.user_data;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import lombok.Data;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import ru.randscheduler.data.annotations.ConvertMapToSet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dimaMJ on 31.01.2018
 */
@Data
@Entity(value = "user_data", noClassnameStored = true)
public class UserData {

    @Id
    private String id;

    @Property(concreteClass = ConcurrentHashMap.class)
    @ConvertMapToSet
    private Map<Integer, SchedulerData> schedulers = Maps.newConcurrentMap();

    private UserData() {
    }

    public UserData(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserData)) return false;
        UserData userData = (UserData) o;
        return Objects.equal(id, userData.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), id);
    }
}
