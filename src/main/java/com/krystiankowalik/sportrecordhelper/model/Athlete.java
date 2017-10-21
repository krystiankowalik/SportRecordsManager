package com.krystiankowalik.sportrecordhelper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "athletes")
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "athlete")
    private List<Score> records;

    public Athlete(String name, String country, List<Score> records) {
        this.name = name;
        this.country = country;
        this.records = records;
    }

    public void addRecord(Score score) {
        if (records == null) {
            records = new ArrayList<>();
        }
        records.add(score);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Athlete{");
        sb.append("name='").append(name).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", records=").append(records);
        sb.append('}');
        return sb.toString();
    }
}
