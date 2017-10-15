package com.krystiankowalik.sportrecordhelper.logic.parser;

import com.krystiankowalik.sportrecordhelper.model.athlete.Athletes;

import java.util.List;

public interface AthleteParser {

    String ATHLETE_DELIMITER = "-";
    String RECORD_DELIMITER = "\\|";

    int RECORD_MEMBERS_COUNT = 3;
    int MIN_SINGLE_ATHLETE_LINE = 3;

    Athletes parseAthletes(List<String> lines);

}
