package com.krystiankowalik.sportrecordhelper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "records")
public class Record {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "distance")
    private int distance;

    @Column(name = "time")
    private BigDecimal time;

    @ManyToOne(cascade = CascadeType.ALL)
    private Athlete athlete;

    //private BigDecimal thousandMetersTime;

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
