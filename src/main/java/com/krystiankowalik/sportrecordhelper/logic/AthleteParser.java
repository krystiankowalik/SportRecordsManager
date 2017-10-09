package com.krystiankowalik.sportrecordhelper.logic;

import com.krystiankowalik.sportrecordhelper.model.Athlete;
import com.krystiankowalik.sportrecordhelper.model.Record;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@AllArgsConstructor
public class AthleteParser {

    private List<String> singleAthleteLines;

    public static final String ATHLETE_DELIMITER = "-";

    public Athlete parseAthlete() {
        Athlete athlete = new Athlete();

        athlete.setName(singleAthleteLines.get(0));
        athlete.setCountry(singleAthleteLines.get(1));

        singleAthleteLines.subList(2, singleAthleteLines.size() - 1)
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
