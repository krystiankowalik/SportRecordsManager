package com.krystiankowalik.sportrecordhelper.logic.parser.batch;

import com.krystiankowalik.sportrecordhelper.logic.parser.athlete.AthleteParser;
import com.krystiankowalik.sportrecordhelper.model.Athlete;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.krystiankowalik.sportrecordhelper.util.Constants.EMPTY;

@Service
public class IterativeBatchAthleteParser implements BatchAthleteParser {


    private AthleteParser athleteParser;

    public IterativeBatchAthleteParser(@Qualifier(value = "iterativeAthleteParser") AthleteParser athleteParser) {
        this.athleteParser = athleteParser;
    }


    @Override
    public List<Athlete> parseAll(List<String> lines) {

        List<Athlete> athletes = new ArrayList<>();
        List<String> singleAthletesLines = new ArrayList<>();

        if (lines != null) {

            skipEmptyLines(lines);

            lines.forEach(line -> {
                if (!line.equals(ATHLETE_DELIMITER)) {
                    singleAthletesLines.add(line);
                } else {
                    Athlete athlete = athleteParser.parse(singleAthletesLines);
                    if (athlete != null) {
                        athletes.add(athlete);
                    }
                    singleAthletesLines.clear();
                }
            });
            Athlete athlete = athleteParser.parse(singleAthletesLines);
            if (athlete != null) {
                athletes.add(athlete);
            }
        }

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

}