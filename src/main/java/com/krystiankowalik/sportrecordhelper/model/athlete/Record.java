package com.krystiankowalik.sportrecordhelper.model.athlete;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

import static com.krystiankowalik.sportrecordhelper.util.Constants.AFTER;
import static com.krystiankowalik.sportrecordhelper.util.Constants.BEFORE;
import static com.krystiankowalik.sportrecordhelper.util.Constants.EQUAL;

public class Record implements Comparable<Record> {

    private LocalDate date;

    private int distance;

    private BigDecimal time;

    private BigDecimal thousandMetersTimeEquivalent;

    public Record(LocalDate date, int distance, BigDecimal time) {
        this.date = date;
        this.distance = distance;
        this.time = time;
        recalculateThousandMetersTimeEquivalent();
    }

    public Record() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
        recalculateThousandMetersTimeEquivalent();
    }

    public BigDecimal getTime() {
        return time;
    }

    public void setTime(BigDecimal time) {
        this.time = time;
        recalculateThousandMetersTimeEquivalent();
    }

    public BigDecimal getThousandMetersTimeEquivalent() {
        return thousandMetersTimeEquivalent;
    }

    private void recalculateThousandMetersTimeEquivalent() {
        if (time != null && distance != 0) {
            thousandMetersTimeEquivalent = time
                    .divide(BigDecimal.valueOf(distance), 15, RoundingMode.CEILING)
                    .multiply(BigDecimal.valueOf(1000));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return distance == record.distance &&
                Objects.equals(date, record.date) &&
                Objects.equals(time, record.time) &&
                Objects.equals(thousandMetersTimeEquivalent, record.thousandMetersTimeEquivalent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, distance, time, thousandMetersTimeEquivalent);
    }

    @Override
    public int compareTo(Record anotherRecord) {

        if (this == anotherRecord) return EQUAL;

        if (anotherRecord == null) {
            return BEFORE;
        }
        if (anotherRecord.getDate() == null) {
            return BEFORE;
        }
        if (this.date == null) {
            return AFTER;
        }

        return -anotherRecord.getDate().compareTo(this.date);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Record{");
        sb.append("date=").append(date);
        sb.append(", distance=").append(distance);
        sb.append(", time=").append(time);
        sb.append(", thousandMetersTimeEquivalent=").append(thousandMetersTimeEquivalent);
        sb.append('}');
        return sb.toString();
    }
}
