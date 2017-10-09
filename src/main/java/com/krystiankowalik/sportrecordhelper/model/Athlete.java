package com.krystiankowalik.sportrecordhelper.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class Athlete {

    private String name;

    private String country;

    private List<Record> records;

    public void addRecord(Record record) {
        if (records == null) {
            records = new ArrayList<>();
        }
        records.add(record);
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
