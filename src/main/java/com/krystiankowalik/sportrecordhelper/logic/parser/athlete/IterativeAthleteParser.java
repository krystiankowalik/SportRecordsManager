package com.krystiankowalik.sportrecordhelper.logic.parser.athlete;

import com.krystiankowalik.sportrecordhelper.logic.parser.score.ScoreParser;
import com.krystiankowalik.sportrecordhelper.logic.parser.validator.AthleteValidator;
import com.krystiankowalik.sportrecordhelper.logic.parser.validator.ScoreValidator;
import com.krystiankowalik.sportrecordhelper.model.Athlete;
import com.krystiankowalik.sportrecordhelper.model.Score;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.krystiankowalik.sportrecordhelper.error.ErrorMessage.PARSING_ERROR;

@Service
public final class IterativeAthleteParser implements AthleteParser {

    private final ScoreValidator scoreValidator;
    private final AthleteValidator athleteValidator;
    private final Logger logger = Logger.getLogger(this.getClass());
    private final ScoreParser scoreParser;

    @Autowired
    public IterativeAthleteParser(ScoreValidator scoreValidator, AthleteValidator athleteValidator, ScoreParser scoreParser) {
        this.scoreValidator = scoreValidator;
        this.athleteValidator = athleteValidator;
        this.scoreParser = scoreParser;
    }

    public Athlete parse(List<String> singleAthleteLines) {
        Athlete athlete = new Athlete();
        if (singleAthleteLines == null) {
            logger.error(PARSING_ERROR + " Nothing to parse from.");
            return null;
        }
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
        } else {
            logger.error(PARSING_ERROR + " too little lines in: " + singleAthleteLines + " : " + singleAthleteLines.size() + " expected: >" + MIN_SINGLE_ATHLETE_LINE);
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
       return scoreParser.parse(scoreLine);
    }


}
