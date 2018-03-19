package ru.randscheduler.tools;

import com.google.common.collect.Lists;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;

/**
 * Created by dimaMJ on 15.03.2018
 */
public class RangeUtils {

    private RangeUtils() {
    }

    public static List<LocalDate> getDatesRange(LocalDate from, LocalDate to) {
        if (from == null || to == null) return Collections.emptyList();
        if (from.isAfter(to)) return Collections.emptyList();

        List<LocalDate> res = Lists.newArrayList(from);
        int days = Period.between(from, to).getDays();

        while (days > 0) {
            res.add(from = from.plusDays(1));
            days--;
        }

        return res;
    }
}
