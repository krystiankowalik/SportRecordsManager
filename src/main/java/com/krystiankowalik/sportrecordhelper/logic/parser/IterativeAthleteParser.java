package com.krystiankowalik.sportrecordhelper.logic.parser;

import com.krystiankowalik.sportrecordhelper.model.athlete.Athlete;
import com.krystiankowalik.sportrecordhelper.model.athlete.Athletes;
import com.krystiankowalik.sportrecordhelper.model.athlete.Record;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public final class IterativeAthleteParser implements AthleteParser {

    @Override
    public Athletes parseAthletes(List<String> allLines) {

        List<String> singleAthleteLines = new ArrayList<>();
        Athletes athletes = new Athletes();

        Optional.ofNullable(allLines)
                .ifPresent(linesList -> {
                    linesList.forEach(line -> {
                        if (!line.trim().equals(ATHLETE_DELIMITER)) {
                            singleAthleteLines.add(line.trim());
                        } else {
                            athletes.add(parseAthlete(singleAthleteLines));
                            singleAthleteLines.clear();
                        }
                    });
                    athletes.add(parseAthlete(singleAthleteLines));

                });


        return athletes;

    }


    private Athlete parseAthlete(List<String> singleAthleteLines) {
        Athlete athlete = new Athlete();
        final int minSingleAthleteLineCount = 3;

        if (singleAthleteLines.size() >= minSingleAthleteLineCount) {

            athlete.setName(singleAthleteLines.get(0));
            athlete.setCountry(singleAthleteLines.get(1));

            singleAthleteLines.subList(2, singleAthleteLines.size())
                    .forEach(recordLine -> {
                        athlete.addRecord(parseRecord(recordLine));
                    });
        }

        return athlete;
    }

    private Record parseRecord(String string) {
        Record record = new Record();
        if (string.contains(RECORD_DELIMITER)) {
            String[] splitRecordLine = string.split(Pattern.quote(RECORD_DELIMITER));

            record.setDate(LocalDate.parse(splitRecordLine[0].trim()));
            record.setDistance(Integer.valueOf(splitRecordLine[1].trim()));
            record.setTime(new BigDecimal(splitRecordLine[2].trim()));
        }
        return record;
    }
}
