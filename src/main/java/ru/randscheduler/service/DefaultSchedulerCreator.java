package ru.randscheduler.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;
import ru.randscheduler.data.SchedulerCreator;
import ru.randscheduler.data.SchedulerViewData;
import ru.randscheduler.data.user_data.Action;
import ru.randscheduler.data.user_data.FilterData;
import ru.randscheduler.tools.RangeUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by dimaMJ on 15.03.2018
 */
@Service
public class DefaultSchedulerCreator implements SchedulerCreator {

    private static final Random rand = new Random();

    @Override
    public SchedulerViewData create(FilterData f) {
        List<LocalDate> datesRange = RangeUtils.getDatesRange(f.getFrom(), f.getTo());

        Set<Integer> selectedRandValues = Sets.newHashSet();
        int days = datesRange.size();
        List<Action> res = Lists.newArrayList();

        for (String userAction : f.getActions()) {

            for (;;) {
                int randVal = rand.nextInt(days);
                if (selectedRandValues.add(randVal)) {
                    res.add(Action.create(f.getFrom().plusDays(randVal), userAction));
                    break;
                }
            }

        }

        return new SchedulerViewData(res, datesRange);
    }

}
