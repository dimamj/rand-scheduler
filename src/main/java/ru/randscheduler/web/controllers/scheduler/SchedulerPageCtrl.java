package ru.randscheduler.web.controllers.scheduler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.val;
import org.mongodb.morphia.query.ArraySlice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.randscheduler.data.SchedulerViewData;
import ru.randscheduler.data.user_data.SchedulerData;
import ru.randscheduler.data.user_data.UserData;
import ru.randscheduler.repository.UserRepository;
import ru.randscheduler.tools.RangeUtils;

import java.time.LocalDate;
import java.util.Map;

import static ru.randscheduler.tools.UserCookieUtils.COOKIE_ID;

/**
 * Created by dimaMJ on 21.03.2018
 */
@Controller
@RequestMapping("/scheduler/{schedulerId}")
@AllArgsConstructor
public class SchedulerPageCtrl {

    private final UserRepository userRepository;

    @GetMapping
    public String getPage(@CookieValue(COOKIE_ID) String userId,
                          @PathVariable int schedulerId, Map<String, Object> model) {
        SchedulerData data = userRepository.get(userId,
                q -> q.disableValidation()
                        .field("schedulers.id").equal(schedulerId)
                        .project("schedulers.$", new ArraySlice(1)))
                .getSchedulers().get(schedulerId);

        SchedulerViewData viewData = new SchedulerViewData(data.getActions(),
                RangeUtils.getDatesRange(data.getFilter().getFrom(), data.getFilter().getTo()));

        model.put("schInfo", new SchedulerViewInfo(LocalDate.now(), data));
        model.put("schViewData", viewData);
        return "/scheduler/view";
    }

    @Data
    public static class SchedulerViewInfo {
        private String name;
        private int futureActionCount;

        public SchedulerViewInfo(LocalDate now, SchedulerData d) {
            this.name = d.getFilter().getSchedulerName();
            this.futureActionCount = (int) d.getActions().stream()
                    .filter(a -> a.getDate().isAfter(now) || a.getDate().isEqual(now))
                    .count();
        }

    }
}
