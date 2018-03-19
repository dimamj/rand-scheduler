package ru.randscheduler.tools;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dimaMJ on 15.03.2018
 */
public class RangeUtilsTest {

    @Test
    public void testGetDatesRange() {
        LocalDate from = LocalDate.of(2018, Month.MARCH, 15);
        LocalDate to = LocalDate.of(2018, Month.MARCH, 20);

        List<LocalDate> res = RangeUtils.getDatesRange(from, to);

        assertTrue(res.size() == 6);

        for (LocalDate date : res) {
            assertEquals(from, date);
            from = from.plusDays(1);
        }

    }
}