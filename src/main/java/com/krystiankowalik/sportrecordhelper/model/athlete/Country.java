package com.krystiankowalik.sportrecordhelper.model.athlete;

import java.util.Objects;

import static com.krystiankowalik.sportrecordhelper.util.Constants.AFTER;
import static com.krystiankowalik.sportrecordhelper.util.Constants.EQUAL;

public class Country implements Comparable<Country> {

    private String name;

    private int count;

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Country{");
        sb.append("name='").append(name).append('\'');
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Country anotherCountry) {

        if (this == anotherCountry) return EQUAL;

        if (anotherCountry == null) return AFTER;

        return Integer.compare(this.count, anotherCountry.count);

    }
}
