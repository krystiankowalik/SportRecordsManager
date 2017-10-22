package com.krystiankowalik.sportrecordhelper.logic.parser.score;

import com.krystiankowalik.sportrecordhelper.logic.parser.validator.ScoreValidator;
import com.krystiankowalik.sportrecordhelper.model.Score;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.krystiankowalik.sportrecordhelper.error.ErrorMessage.PARSING_ERROR;
import static com.krystiankowalik.sportrecordhelper.logic.parser.athlete.AthleteParser.SCORE_DELIMITER;
@Service
public class IterativeScoreParser implements ScoreParser {

    private final ScoreValidator scoreValidator;
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    public IterativeScoreParser(ScoreValidator scoreValidator) {
        this.scoreValidator = scoreValidator;
    }

    @Override
    public Score parse(String scoreLine) {
        Score score = new Score();
        if (scoreValidator.isValidRecordLine(scoreLine)) {
            String[] splitRecordLine = scoreLine.split(SCORE_DELIMITER);

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
