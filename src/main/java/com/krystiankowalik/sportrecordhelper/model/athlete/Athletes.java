package com.krystiankowalik.sportrecordhelper.model.athlete;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Athletes {

    private Set<Athlete> athletes;

    private List<Country> countryList;

    public void add(Athlete athlete) {

        athletes.add(athlete);

        String countryName = athlete.getCountry();
        Country country = new Country(countryName);

        if (countryList.contains(country)) {
            country = getCountry(country);
            countryList.remove(country);
            country.setCount(country.getCount() + 1);
            countryList.add(country);

        } else {
            Country newCountry = new Country(countryName);
            newCountry.setCount(1);
            countryList.add(newCountry);
        }

    }

    public boolean isEmpty() {
        return athletes.isEmpty();
    }

    public void addAll(Athletes athletes) {
        athletes.getAthletes().forEach(this::add);
    }

    private Country getCountry(final Country country) {

        Country returnCountry = null;
        for (Country c : countryList) {
            if (c != null && c.equals(country)) {
                returnCountry = c;
            }
        }
        return returnCountry;
    }

    public Athletes() {
        this.athletes = new TreeSet<>();
        this.countryList = new ArrayList<>();
    }


    public Stream<Athlete> getAthletes() {
        return athletes.stream();
    }

    public void printAllThousandMetersTimes() {
        System.out.println(System.lineSeparator());
        System.out.println("Average athletes' timings: ");
        System.out.println(System.lineSeparator());

        StringBuilder sb = new StringBuilder();
        athletes.forEach(a -> {
            sb.setLength(0);
            sb.append(a.getName());
            sb.append(" - 1000m in ");
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

    public void printTopCountries(int requestedTopCount) {

        System.out.println(System.lineSeparator());

        countryList.sort((c1, c2) -> -c1.compareTo(c2));

        List<Integer> countriesCounts = countryList.stream()
                .map(Country::getCount)
                .distinct()
                .collect(Collectors.toList());

        int topCount;

        if (countriesCounts.size() >= requestedTopCount) {
            topCount = requestedTopCount;
        } else {
            topCount = countriesCounts.size();
        }

        List<Integer> topCounts = countriesCounts.stream().limit(topCount).collect(Collectors.toList());

        List<Country> topCountries = countryList.stream().filter(c -> topCounts.contains(c.getCount())).collect(Collectors.toList());

        final StringBuilder sb = new StringBuilder();

        sb.append("Top ");
        sb.append(requestedTopCount);
        sb.append(" Countries");
        sb.append(System.lineSeparator());

        topCountries.forEach(c -> {
            sb.append("- ");
            sb.append(c.getName());
            sb.append(" (");
            sb.append(c.getCount());
            sb.append(")");
            sb.append(System.lineSeparator());
        });

        System.out.println(sb.toString());

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
                        System.out.println("First record registered: " + date.format(DateTimeFormatter.ISO_LOCAL_DATE));
                    }
                });
    }

}
