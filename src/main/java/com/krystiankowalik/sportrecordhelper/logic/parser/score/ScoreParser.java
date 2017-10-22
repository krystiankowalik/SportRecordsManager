package com.krystiankowalik.sportrecordhelper.logic.parser.score;

import com.krystiankowalik.sportrecordhelper.model.Score;
import org.springframework.stereotype.Service;

@Service
public interface ScoreParser {

    String SCORE_DELIMITER = "\\|";

    int SCORE_MEMBERS_COUNT = 3;

    Score parse(String scoreLine);
}
