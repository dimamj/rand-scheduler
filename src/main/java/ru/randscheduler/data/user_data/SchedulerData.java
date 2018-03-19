package ru.randscheduler.data.user_data;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.randscheduler.data.annotations.MapKey;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Created by dimaMJ on 05.02.2018
 */
@Data
@AllArgsConstructor
public class SchedulerData {
    @MapKey
    private int id;

    private FilterData filter;
    private List<Action> actions;

    private SchedulerData() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchedulerData)) return false;
        SchedulerData that = (SchedulerData) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
