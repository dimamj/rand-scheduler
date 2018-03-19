package ru.randscheduler.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.randscheduler.data.user_data.Action;
import ru.randscheduler.data.user_data.FilterData;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by dimaMJ on 15.03.2018
 */
public interface SchedulerCreator {
    SchedulerViewData create(FilterData filterData);
}
