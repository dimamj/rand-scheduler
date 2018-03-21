package ru.randscheduler.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.randscheduler.data.user_data.Action;
import ru.randscheduler.data.user_data.FilterData;
import ru.randscheduler.data.user_data.SchedulerData;
import ru.randscheduler.data.user_data.UserData;
import ru.randscheduler.repository.UserRepository;

import java.util.List;

/**
 * Created by dimaMJ on 19.03.2018
 */
@Service
@AllArgsConstructor
public class UserSchedulerService {

    private final UserRepository userRepository;

    public void addScheduler(String userId, FilterData f, List<Action> actions) {
        UserData userData = userRepository.get(userId);
        int newSchedulerId = userData.getSchedulers().size() + 1;
        userData.getSchedulers().put(newSchedulerId, new SchedulerData(newSchedulerId, f, actions));
        userRepository.save(userData);
    }
}
