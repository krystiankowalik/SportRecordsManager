package com.krystiankowalik.sportrecordhelper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class Record {

    private LocalDate date;

    private int distance;

    private BigDecimal time;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Record{");
        sb.append("date=").append(date);
        sb.append(", distance=").append(distance);
        sb.append(", time=").append(time);
        sb.append('}');
        return sb.toString();
    }
}
