package ru.randscheduler.data.user_data;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by dimaMJ on 05.02.2018
 */
@Data
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class Action {
    private LocalDate date;
    private String action;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Action)) return false;
        Action action1 = (Action) o;
        return Objects.equal(date, action1.date) &&
                Objects.equal(action, action1.action);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(date, action);
    }
}
