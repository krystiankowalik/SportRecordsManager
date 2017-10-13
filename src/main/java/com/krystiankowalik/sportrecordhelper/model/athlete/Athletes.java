package com.krystiankowalik.sportrecordhelper.model.athlete;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class Athletes {

    private Set<Athlete> athletes;
    private Map<String, Integer> countries;

    public void add(Athlete athlete) {
        athletes.add(athlete);

        String countryName = athlete.getCountry();

        if (countries.containsKey(countryName)) {
            countries.put(countryName, countries.get(countryName) + 1);
        } else {
            countries.put(countryName, 1);
        }
    }

    public Athletes(Set<Athlete> athletes) {
        this.athletes = new TreeSet<>();
        this.countries = new LinkedHashMap<>();
    }


    public Stream<Athlete> getAthletes() {
        return athletes.stream();
    }

    public void printAllThousandMetersTimes() {
        System.out.println(System.lineSeparator());
        System.out.println("Listed athletes: ");
        System.out.println(System.lineSeparator());

        StringBuilder sb = new StringBuilder();
        athletes.forEach(a -> {
            sb.setLength(0);
            sb.append(a.getName());
            sb.append(" - 1000m w ");
            sb.append(a.getRecords()
                    .stream()
                    .map(Record::getThousandMetersTimeEquivalent)
                    .mapToDouble(BigDecimal::longValue)
                    .average()
                    .orElse(0));
            sb.append("s");
            System.out.println(sb.toString());

        });
    }

    public void printTopCountries() {

    }

    public void printFirstRecordDate() {
        System.out.println(System.lineSeparator());

        athletes.stream()
                .flatMap(r -> r.getRecords()
                        .stream())
                .findFirst()
                .ifPresent(record -> {
                    LocalDate date = record.getDate();
                    if (date != null) {
                        System.out.println("Pierwszy wynik zarejestrowano: " + date.format(DateTimeFormatter.ISO_LOCAL_DATE));
                    }
                });
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Athletes{");
        sb.append("athletes=").append(athletes);
        sb.append(", countries=").append(countries);
        sb.append('}');
        return sb.toString();
    }
}
