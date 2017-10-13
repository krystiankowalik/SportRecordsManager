package com.krystiankowalik.sportrecordhelper.model.athlete;

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

        String country = athlete.getCountry();

        if (countries.containsKey(country)) {
            int currentCount = countries.get(country);
            countries.put(country, ++currentCount);
        } else {
            countries.put(country, 1);
        }
    }

    public Athletes(Set<Athlete> athletes) {
        this.athletes = new TreeSet<>();
        this.countries = new LinkedHashMap<>();
    }


    public Stream<Athlete> getAthletes(){
        return athletes.stream();
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
