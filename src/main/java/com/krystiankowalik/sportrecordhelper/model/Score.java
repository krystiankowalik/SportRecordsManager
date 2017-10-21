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

@Entity(name = "scores")
public class Score {


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
    @JoinColumn(name = "athlete_id", referencedColumnName = "id")
    private Athlete athlete;

    public Score(LocalDate date, int distance, BigDecimal time) {
        this.date = date;
        this.distance = distance;
        this.time = time;
    }

    public Score(int id, LocalDate date, int distance, BigDecimal time) {
        this.id = id;
        this.date = date;
        this.distance = distance;
        this.time = time;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Score{");
        sb.append("date=").append(date);
        sb.append(", distance=").append(distance);
        sb.append(", time=").append(time);
        sb.append('}');
        return sb.toString();
    }
}
