package ru.randscheduler.web.controllers.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.randscheduler.data.MonthWeek;
import ru.randscheduler.data.SchedulerCreator;
import ru.randscheduler.data.SchedulerViewData;
import ru.randscheduler.data.user_data.FilterData;
import ru.randscheduler.repository.UserRepository;
import ru.randscheduler.tools.UserCookieUtils;

import java.time.LocalDate;
import java.time.YearMonth;

import static ru.randscheduler.tools.UserCookieUtils.COOKIE_ID;

/**
 * Created by dimaMJ on 14.03.2018
 */
@RestController
@RequestMapping("/scheduler")
@RequiredArgsConstructor
public class SchedulerApiCtrl {

    private final SchedulerCreator defaultSchedulerCreator;
    private final UserRepository userRepository;

    @GetMapping("/create")
    public SchedulerViewData create(FilterData filterData) {
        upFromToDates(filterData);
        return defaultSchedulerCreator.create(filterData);
    }

    @PostMapping(value = "/remove", produces = "text/plain")
    public ResponseEntity remove(@CookieValue(COOKIE_ID) String userId,
                                 @RequestParam("id") int id) {
        userRepository.removeScheduler(userId, id);
        return ResponseEntity.ok("ok");
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

        if (f.getFrom().isBefore(now)) f.setFrom(now);
    }


}
