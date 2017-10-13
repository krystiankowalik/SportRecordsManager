package com.krystiankowalik.sportrecordhelper.model.athlete;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Athlete implements Comparable<Athlete> {

    private String name;

    private String country;

    private Set<Record> records;

    public void addRecord(Record record) {
        if (records == null) {
            records = new TreeSet<>();
        }
        records.add(record);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }

    @Override
    public int compareTo(Athlete anotherAthlete) {
        if (anotherAthlete == null) {
            return 0;
        }
        if (this.records == null) {
            return 0;
        }
        if (anotherAthlete.getRecords() == null) {
            return 0;
        }
        if (!anotherAthlete.getRecords().stream().findFirst().isPresent() |
                !this.records.stream().findFirst().isPresent()) {
            return 0;
        }
        return anotherAthlete.getRecords().stream().findFirst().get()
                .compareTo(this.records.stream().findFirst().get());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Athlete athlete = (Athlete) o;
        return Objects.equals(name, athlete.name) &&
                Objects.equals(country, athlete.country) &&
                Objects.equals(records, athlete.records);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, records);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Athlete{");
        sb.append("name='").append(name).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", records=").append(records);
        sb.append('}');
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
