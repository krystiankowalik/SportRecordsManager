package com.krystiankowalik.sportrecordhelper.logic.parser;

import com.krystiankowalik.sportrecordhelper.logic.parser.validator.AthleteValidator;
import com.krystiankowalik.sportrecordhelper.logic.parser.validator.RecordValidator;
import com.krystiankowalik.sportrecordhelper.model.athlete.Athlete;
import com.krystiankowalik.sportrecordhelper.model.athlete.Athletes;
import com.krystiankowalik.sportrecordhelper.model.athlete.Record;
import com.krystiankowalik.sportrecordhelper.model.error.Error;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.krystiankowalik.sportrecordhelper.util.Constants.EMPTY;

public final class IterativeAthleteParser implements AthleteParser {


    private RecordValidator recordValidator;
    private AthleteValidator athleteValidator;

    public IterativeAthleteParser() {
        this.recordValidator = new RecordValidator();
        this.athleteValidator = new AthleteValidator();
    }

    @Override
    public Athletes parseAthletes(List<String> allLines) {
        skipEmptyLines(allLines);

        List<String> singleAthleteLines = new ArrayList<>();
        Athletes athletes = new Athletes();

        Optional.ofNullable(allLines)
                .ifPresent(linesList -> {

                    linesList.forEach(line -> {
                        if (!line.trim().equals(ATHLETE_DELIMITER)) {
                            singleAthleteLines.add(line.trim());
                        } else {
                            Athlete athlete = parseAthlete(singleAthleteLines);
                            addAthleteIfValid(athlete, athletes, singleAthleteLines);
                            singleAthleteLines.clear();
                        }
                    });

                    Athlete athlete = parseAthlete(singleAthleteLines);
                    addAthleteIfValid(athlete, athletes, singleAthleteLines);
                });


        return athletes;

    }

    private void skipEmptyLines(List<String> lines) {
        for (int i = 0; i < lines.size(); ++i) {
            if (lines.get(i).trim().equals(EMPTY)) {
                lines.remove(i);
                --i;
                if (lines.size() == 0) {
                    break;
                }
            }
        }
    }

    private void addAthleteIfValid(Athlete athlete, Athletes athletes, List<String> singleAthleteLines) {
        if (athlete.isValid()) {
            athletes.add(athlete);
        } else {
            Error.print(Error.PARSING_ERROR, singleAthleteLines.toString() + "\n", false);
        }
    }


    private Athlete parseAthlete(List<String> singleAthleteLines) {
        Athlete athlete = new Athlete();

        if (singleAthleteLines.size() >= MIN_SINGLE_ATHLETE_LINE) {
            String athleteName = singleAthleteLines.get(0);
            String athleteCountry = singleAthleteLines.get(1);
            if (athleteValidator.isValidAthlete(athleteName, athleteCountry)) {
                athlete.setName(athleteName);
                athlete.setCountry(athleteCountry);

            }
            singleAthleteLines.subList(2, singleAthleteLines.size())
                    .forEach(recordLine -> {
                        Record record = parseRecord(recordLine);
                        if (record != null) {
                            athlete.addRecord(record);
                        }
                    });
        }

        return athlete;
    }

    private Record parseRecord(String recordLine) {
        Record record = new Record();
        if (recordValidator.isValidRecordLine(recordLine)) {
            String[] splitRecordLine = recordLine.split(RECORD_DELIMITER);

            record.setDate(LocalDate.parse(splitRecordLine[0].trim()));
            record.setDistance(Integer.valueOf(splitRecordLine[1].trim()));
            record.setTime(new BigDecimal(splitRecordLine[2].trim()));
            return record;
        }
        return null;
    }


}
