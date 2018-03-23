package ru.randscheduler.web.controllers.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.randscheduler.data.user_data.Action;
import ru.randscheduler.data.user_data.FilterData;
import ru.randscheduler.data.user_data.SchedulerType;
import ru.randscheduler.service.UserSchedulerService;
import ru.randscheduler.tools.UserCookieUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static ru.randscheduler.tools.UserCookieUtils.COOKIE_ID;

/**
 * Created by dimaMJ on 14.03.2018
 */
@Controller
@RequestMapping("/scheduler/add")
@AllArgsConstructor
public class AddSchedulerCtrl {

    private final UserSchedulerService service;

    @GetMapping
    public String getPage(Map<String, Object> model) {
        model.put("scheduler_types", SchedulerType.getTypes());
        return "/scheduler/add";
    }

    @PostMapping(produces = "text/plain")
    public ResponseEntity<String> save(@RequestBody PostData data, @CookieValue(COOKIE_ID) String userId) {
        service.addScheduler(userId, data.filterData, data.actions);
        return ResponseEntity.ok("ok");
    }

    @Data
    public static class PostData {
        private FilterData filterData;
        private List<Action> actions;
    }
}
