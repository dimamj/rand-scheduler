package ru.randscheduler.data;

import com.google.common.base.Objects;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

/**
 * Created by dimaMJ on 14.03.2018
 */
@Getter
public final class MonthWeek implements Comparable<MonthWeek> {
    private final LocalDate from;
    private final LocalDate to;

    public MonthWeek(LocalDate date) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        if (dayOfWeek == 1) {
            from = date;
            to = date.plusDays(6);
        } else if (dayOfWeek == 7) {
            from = date.minusDays(6);
            to = date;
        } else {
            from = date.minusDays(dayOfWeek - 1);
            to = date.plusDays(Math.abs(dayOfWeek - 7));
        }
    }

    public static MonthWeek from(LocalDate date) {
        return new MonthWeek(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MonthWeek)) return false;
        MonthWeek monthWeek = (MonthWeek) o;
        return Objects.equal(from, monthWeek.from) &&
                Objects.equal(to, monthWeek.to);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(from, to);
    }

    @Override
    public int compareTo(@NotNull MonthWeek other) {
        return this.from.compareTo(other.from);
    }
}
