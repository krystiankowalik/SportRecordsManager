package com.krystiankowalik.sportrecordhelper.logic.parser;

import com.krystiankowalik.sportrecordhelper.model.Athlete;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BatchAthleteParser {

    String ATHLETE_DELIMITER = "-";

    List<Athlete> parseAthletes(List<String> lines);

}
