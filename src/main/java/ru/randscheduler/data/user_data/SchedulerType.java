package ru.randscheduler.data.user_data;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import javafx.util.Pair;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * Created by dimaMJ on 05.02.2018
 */
@Getter
public enum SchedulerType {
    CURRENT_WEEK("Текущая неделя"), NEXT_WEEK("Следующая неделя"),
    CURRENT_MONTH("Текущий месяц"), NEXT_MONTH("Следующий месяц");

    SchedulerType(String viewName) {
        this.viewName = viewName;
    }

    private final String viewName;


    private static Map<SchedulerType, String> map = Maps.newLinkedHashMap();

    static {
        for (SchedulerType type : SchedulerType.values()) {
            map.put(type, type.viewName);
        }
    }

    public static Map<SchedulerType, String> getTypes() {
        return map;
    }
}
