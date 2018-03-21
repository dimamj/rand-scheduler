package ru.randscheduler.data.user_data;

import com.google.common.collect.Sets;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Created by dimaMJ on 05.02.2018
 */
@Data
public class FilterData {
    private String schedulerName;

    private LocalDate from;
    private LocalDate to;

    private List<String> actions;
    private SchedulerType type;


    public void upDates(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }
}
