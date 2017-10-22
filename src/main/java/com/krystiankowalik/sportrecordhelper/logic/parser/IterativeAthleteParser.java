package com.krystiankowalik.sportrecordhelper.logic.parser;

import com.krystiankowalik.sportrecordhelper.logic.parser.validator.AthleteValidator;
import com.krystiankowalik.sportrecordhelper.logic.parser.validator.RecordValidator;
import com.krystiankowalik.sportrecordhelper.model.Athlete;
import com.krystiankowalik.sportrecordhelper.model.Score;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.krystiankowalik.sportrecordhelper.logic.parser.error.ErrorMessage.PARSING_ERROR;

@Service
public final class IterativeAthleteParser implements AthleteParser {


    private RecordValidator recordValidator;
    private AthleteValidator athleteValidator;
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    public IterativeAthleteParser(RecordValidator recordValidator, AthleteValidator athleteValidator) {
        this.recordValidator = recordValidator;
        this.athleteValidator = athleteValidator;
    }

    public Athlete parseAthlete(List<String> singleAthleteLines) {
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
                        Score score = parseScore(recordLine);
                        if (score != null) {
                            athlete.addRecord(score);
                        }
                    });
        }

        if (athlete.getScores() != null) {
            athlete.getScores().forEach(score -> score.setAthlete(athlete));
        }
        if (athleteValidator.isValidAthlete(athlete.getName(), athlete.getCountry())) {
            return athlete;
        } else {
            logger.error(PARSING_ERROR + "in: " + singleAthleteLines);
            return null;

        }
    }

    private Score parseScore(String scoreLine) {
        Score score = new Score();
        if (recordValidator.isValidRecordLine(scoreLine)) {
            String[] splitRecordLine = scoreLine.split(RECORD_DELIMITER);

            score.setDate(LocalDate.parse(splitRecordLine[0].trim()));
            score.setDistance(Integer.valueOf(splitRecordLine[1].trim()));
            score.setTime(new BigDecimal(splitRecordLine[2].trim()));
            return score;
        } else {
            logger.error(PARSING_ERROR + " in score line: " + scoreLine);
        }
        return null;
    }


}
