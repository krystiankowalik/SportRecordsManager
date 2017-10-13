package com.krystiankowalik.sportrecordhelper.logic;

import com.krystiankowalik.sportrecordhelper.model.athlete.Athlete;
import com.krystiankowalik.sportrecordhelper.model.athlete.Athletes;
import com.krystiankowalik.sportrecordhelper.model.athlete.Record;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Pattern;

public final class AthleteParser {

    public static final String ATHLETE_DELIMITER = "-";

    private List<String> allLines;

    public AthleteParser(List<String> allLines) {
        this.allLines = allLines;
    }

    public Athletes parseAthletes() {

        List<String> singleAthleteLines = new ArrayList<>();
        Athletes athletes = new Athletes(new TreeSet<>());

        allLines.forEach(line -> {
            if (!line.trim().equals(ATHLETE_DELIMITER)) {
                singleAthleteLines.add(line.trim());
            } else {
                athletes.add(parseAthlete(singleAthleteLines));
                singleAthleteLines.clear();
            }
        });
        athletes.add(parseAthlete(singleAthleteLines));

        return athletes;

    }


    private Athlete parseAthlete(List<String> singleAthleteLines) {
        Athlete athlete = new Athlete();

        athlete.setName(singleAthleteLines.get(0));
        athlete.setCountry(singleAthleteLines.get(1));

        singleAthleteLines.subList(2, singleAthleteLines.size())
                .forEach(recordLine -> {
                    athlete.addRecord(parseRecord(recordLine));
                });

        return athlete;
    }

    private Record parseRecord(String string) {
        Record record = new Record();
        String[] splitRecordLine = string.split(Pattern.quote("|"));

        record.setDate(LocalDate.parse(splitRecordLine[0].trim()));
        record.setDistance(Integer.valueOf(splitRecordLine[1].trim()));
        record.setTime(new BigDecimal(splitRecordLine[2].trim()));

        return record;
    }
}
