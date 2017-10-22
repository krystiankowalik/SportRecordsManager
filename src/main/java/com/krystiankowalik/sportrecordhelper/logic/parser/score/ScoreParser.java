package com.krystiankowalik.sportrecordhelper.logic.parser.score;

import com.krystiankowalik.sportrecordhelper.model.Score;
import org.springframework.stereotype.Service;

@Service
public interface ScoreParser {

    Score parse(String scoreLine);
}
