package com.krystiankowalik.sportrecordhelper.logic.parser;

import com.krystiankowalik.sportrecordhelper.model.athlete.Athletes;

import java.util.List;

public interface AthleteParser {

    String ATHLETE_DELIMITER = "-";

    Athletes parseAthletes(List<String> lines);

}
