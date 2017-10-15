package com.krystiankowalik.sportrecordhelper.logic.parser;

import com.krystiankowalik.sportrecordhelper.model.athlete.Athlete;
import com.krystiankowalik.sportrecordhelper.model.athlete.Athletes;
import com.krystiankowalik.sportrecordhelper.model.athlete.Record;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

    private Record parseRecord(String recordLine) {
        Record record = new Record();
        if (isValidRecordLine(recordLine)) {
            String[] splitRecordLine = recordLine.split(Pattern.quote(RECORD_DELIMITER));

            record.setDate(LocalDate.parse(splitRecordLine[0].trim()));
            record.setDistance(Integer.valueOf(splitRecordLine[1].trim()));
            record.setTime(new BigDecimal(splitRecordLine[2].trim()));
        }
        return record;
    }

    private boolean isValidRecordLine(String string) {
        if (!string.matches("(.*\\|.*){2}")) {
            return false;
        }

        String[] splitLine = string.split(RECORD_DELIMITER);

        if (splitLine.length != 3) {
            return false;
        }

        try {
            LocalDate.parse(splitLine[0]);
        } catch (DateTimeParseException e) {
            return false;
        }

        /*if (!splitLine[0].matches("^\\d{4}[\\-\\/\\s]?((((0[13578])|(1[02]))[\\-\\/\\s]?(([0-2][0-9])|(3[01])))|(((0[469])|(11))[\\-\\/\\s]?(([0-2][0-9])|(30)))|(02[\\-\\/\\s]?[0-2][0-9]))$\n")) {
            return false;
        }*/

        if (!splitLine[1].matches("^[1-9]\\d+$")) {
            return false;
        }
        if (!splitLine[2].matches("^[1-9]\\d*(\\.\\d+)?$")) {
            return false;
        }


        return true;
    }

    public static void main(String[] args) {
        //System.out.println("dupa|dupa|dupa".matches("(.*\\|.*){2}"));
        //System.out.println(new IterativeAthleteParser().isValidRecordLine("dupa|dupa|dupa"));

        System.out.println(new IterativeAthleteParser().isValidRecordLine("2015-03-15|15|2.3"));
    }
}
