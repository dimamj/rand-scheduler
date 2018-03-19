package ru.randscheduler.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.randscheduler.data.MonthWeek;
import ru.randscheduler.data.SchedulerCreator;
import ru.randscheduler.data.SchedulerViewData;
import ru.randscheduler.data.user_data.FilterData;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * Created by dimaMJ on 14.03.2018
 */
@RestController
@RequestMapping("/scheduler")
@RequiredArgsConstructor
public class SchedulerCreatorCtrl {

    private final SchedulerCreator defaultSchedulerCreator;

    @GetMapping("/create")
    public SchedulerViewData create(FilterData filterData) {
        upFromToDates(filterData);
        return defaultSchedulerCreator.create(filterData);
    }

    private void upFromToDates(FilterData f) {
        LocalDate now = LocalDate.now();

        switch (f.getType()) {
            default:
            case CURRENT_WEEK:
                MonthWeek week = MonthWeek.from(now);
                f.upDates(week.getFrom(), week.getTo());
                break;
            case NEXT_WEEK:
                week = new MonthWeek(now.plusWeeks(1));
                f.upDates(week.getFrom(), week.getTo());
                break;
            case CURRENT_MONTH:
                YearMonth month = YearMonth.from(now);
                f.upDates(month.atDay(1), month.atEndOfMonth());
                break;
            case NEXT_MONTH:
                month = YearMonth.from(now.plusMonths(1));
                f.upDates(month.atDay(1), month.atEndOfMonth());
                break;
        }
    }


}
