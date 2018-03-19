package ru.randscheduler.data;

import lombok.Data;
import ru.randscheduler.data.user_data.Action;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by dimaMJ on 19.03.2018
 */
@Data
public class SchedulerViewData {
    private List<Action> actions;
    private List<LocalDate> dates;
    private LocalDate from;
    private LocalDate to;

    public SchedulerViewData(List<Action> actions, List<LocalDate> dates) {
        this.actions = actions;
        this.from = dates.get(0);
        this.to = dates.get(dates.size() - 1);

        if (dates.get(0).getDayOfWeek().getValue() != 1) {
            LocalDate firstDate = dates.get(0);
            int needDays = firstDate.getDayOfWeek().getValue() - 1;
            while (needDays != 0) {
                dates.add(0, firstDate = firstDate.minusDays(1));
                needDays--;
            }
        }

        this.dates = dates;
    }
}
