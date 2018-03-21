package ru.randscheduler.web.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.randscheduler.data.user_data.FilterData;
import ru.randscheduler.data.user_data.SchedulerData;
import ru.randscheduler.repository.UserRepository;
import ru.randscheduler.service.UserSchedulerService;
import ru.randscheduler.tools.UserCookieUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.randscheduler.tools.UserCookieUtils.COOKIE_ID;

/**
 * Created by dimaMJ on 30.01.2018
 */
@Controller
@RequestMapping(value = {"/", "/list"})
@AllArgsConstructor
public class SchedulerListCtrl {

    private final UserRepository userRepository;

    @GetMapping
    public String getPage(@CookieValue(COOKIE_ID) String userId,
                          Map<String, Object> model) {
        List<SchedulerData> userSchedulers = userRepository.getSchedulers(userId);
        model.put("schedulers", userSchedulers.stream()
                .map(SchedulerShortViewData::new)
                .collect(Collectors.toList()));

        return "/list";
    }

    @Data
    public static class SchedulerShortViewData {
        private int id;
        private FilterData filterData;
        private int totalActions;
        private int futureActionCount;

        public SchedulerShortViewData(SchedulerData d) {
            LocalDate now = LocalDate.now();
            this.id = d.getId();
            this.filterData = d.getFilter();
            this.totalActions = d.getActions().size();
            this.futureActionCount = (int) d.getActions().stream()
                    .filter(a -> a.getDate().isAfter(now) || a.getDate().isEqual(now))
                    .count();
        }
    }
}
