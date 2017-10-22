package com.krystiankowalik.sportrecordhelper.logic.parser.athlete;

import com.krystiankowalik.sportrecordhelper.logic.parser.score.ScoreParser;
import com.krystiankowalik.sportrecordhelper.logic.parser.validator.AthleteValidator;
import com.krystiankowalik.sportrecordhelper.model.Athlete;
import com.krystiankowalik.sportrecordhelper.model.Score;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.krystiankowalik.sportrecordhelper.error.ErrorMessage.PARSING_ERROR;

@Service
public final class IterativeAthleteParser implements AthleteParser {

    private final Logger logger = Logger.getLogger(this.getClass());

    private final AthleteValidator athleteValidator;
    private final ScoreParser scoreParser;

    @Autowired
    public IterativeAthleteParser(AthleteValidator athleteValidator, ScoreParser scoreParser) {
        this.athleteValidator = athleteValidator;
        this.scoreParser = scoreParser;
    }

    public Athlete parse(List<String> singleAthleteLines) {
        Athlete athlete = new Athlete();

        if (!isValidAthlete(singleAthleteLines)) {
            logger.error(PARSING_ERROR + "while parsing athlete from: " + singleAthleteLines);
            return null;
        }

        parseName(singleAthleteLines, athlete);
        parseCountry(singleAthleteLines, athlete);
        parseScores(singleAthleteLines, athlete);

        return athlete;
    }


    private void parseName(List<String> singleAthleteLines, Athlete athlete) {
        athlete.setName(singleAthleteLines.get(0));
    }

    private void parseCountry(List<String> singleAthleteLines, Athlete athlete) {
        athlete.setCountry(singleAthleteLines.get(1));
    }

    private void parseScores(List<String> singleAthleteLines, Athlete athlete) {
        singleAthleteLines.subList(2, singleAthleteLines.size())
                .forEach(scoreLine -> {
                    Score score = parseScore(scoreLine);
                    if (score != null) {
                        athlete.addRecord(score);
                    }
                });
        populateScoresWithAthlete(athlete);

    }

    private Score parseScore(String scoreLine) {
        return scoreParser.parse(scoreLine);
    }

    private boolean isValidAthlete(List<String> lines) {
        return athleteValidator.isValidAthlete(lines);
    }

    private void populateScoresWithAthlete(Athlete athlete) {
        if (athlete.getScores() != null) {
            athlete.getScores().forEach(score -> score.setAthlete(athlete));
        }
    }

}
