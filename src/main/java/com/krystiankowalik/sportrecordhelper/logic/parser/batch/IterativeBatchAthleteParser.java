package com.krystiankowalik.sportrecordhelper.logic.parser.batch;

import com.krystiankowalik.sportrecordhelper.logic.parser.athlete.AthleteParser;
import com.krystiankowalik.sportrecordhelper.model.Athlete;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.krystiankowalik.sportrecordhelper.error.ErrorMessage.PARSING_ERROR;
import static com.krystiankowalik.sportrecordhelper.error.ErrorMessage.RECORD_SKIPPED;
import static com.krystiankowalik.sportrecordhelper.util.Constants.EMPTY;

@Service
public final class IterativeBatchAthleteParser implements BatchAthleteParser {

    private final Logger logger = Logger.getLogger(this.getClass());

    private final AthleteParser athleteParser;


    public IterativeBatchAthleteParser(@Qualifier(value = "iterativeAthleteParser") AthleteParser athleteParser) {
        this.athleteParser = athleteParser;
    }


    @Override
    public List<Athlete> parseAll(List<String> lines) {

        List<Athlete> athletes = new ArrayList<>();
        List<String> singleAthleteLines = new ArrayList<>();

        if (lines != null) {

            skipEmptyLines(lines);

            lines.forEach(line -> {
                if (!line.equals(ATHLETE_DELIMITER)) {
                    singleAthleteLines.add(line);
                } else {
                    addParsedAthlete(singleAthleteLines, athletes);
                    singleAthleteLines.clear();
                }
            });
            addParsedAthlete(singleAthleteLines, athletes);
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

    private Athlete parseAthlete(List<String> singleAthleteLines) {
        return athleteParser.parse(singleAthleteLines);
    }

    private void addParsedAthlete(List<String> singleAthleteLines, List<Athlete> athletes) {
        Athlete athlete = parseAthlete(singleAthleteLines);
        if (athlete != null) {
            athletes.add(athlete);
        } else {
            logger.error(RECORD_SKIPPED + "due to " + PARSING_ERROR);
        }
    }

}
