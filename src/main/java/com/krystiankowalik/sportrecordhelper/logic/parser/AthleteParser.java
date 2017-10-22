package com.krystiankowalik.sportrecordhelper.logic.parser;

import com.krystiankowalik.sportrecordhelper.model.Athlete;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AthleteParser {

    String RECORD_DELIMITER = "\\|";

    int RECORD_MEMBERS_COUNT = 3;
    int MIN_SINGLE_ATHLETE_LINE = 3;

    Athlete parseAthlete(List<String> lines);

}
