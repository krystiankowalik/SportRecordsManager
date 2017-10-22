package com.krystiankowalik.sportrecordhelper.logic.parser.athlete;

import com.krystiankowalik.sportrecordhelper.model.Athlete;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AthleteParser {

    String SCORE_DELIMITER = "\\|";

    int SCORE_MEMBERS_COUNT = 3;
    int MIN_SINGLE_ATHLETE_LINE = 3;

    Athlete parse(List<String> lines);

}
