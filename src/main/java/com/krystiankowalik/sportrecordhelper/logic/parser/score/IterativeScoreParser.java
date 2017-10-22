package com.krystiankowalik.sportrecordhelper.logic.parser.score;

import com.krystiankowalik.sportrecordhelper.logic.parser.validator.ScoreValidator;
import com.krystiankowalik.sportrecordhelper.model.Score;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.krystiankowalik.sportrecordhelper.error.ErrorMessage.PARSING_ERROR;

@Service
public final class IterativeScoreParser implements ScoreParser {

    private final ScoreValidator scoreValidator;
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    public IterativeScoreParser(ScoreValidator scoreValidator) {
        this.scoreValidator = scoreValidator;
    }

    @Override
    public Score parse(String scoreLine) {
        Score score = new Score();

        if (!scoreValidator.isValidScoreLine(scoreLine)) {
            logger.error(PARSING_ERROR + " in score line: " + scoreLine);
            return null;
        }

        String[] splitScoreLine = scoreLine.split(SCORE_DELIMITER);

        parseDate(splitScoreLine, score);
        parseDistance(splitScoreLine, score);
        parseTime(splitScoreLine, score);

        return score;
    }


    private void parseDate(String[] splitScoreLine, Score score) {
        score.setDate(LocalDate.parse(splitScoreLine[0].trim()));
    }

    private void parseTime(String[] splitScoreLine, Score score) {
        score.setTime(new BigDecimal(splitScoreLine[2].trim()));
    }

    private void parseDistance(String[] splitScoreLine, Score score) {
        score.setDistance(Integer.valueOf(splitScoreLine[1].trim()));
    }


}
