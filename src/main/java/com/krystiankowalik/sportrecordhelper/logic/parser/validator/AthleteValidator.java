package com.krystiankowalik.sportrecordhelper.logic.parser.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.krystiankowalik.sportrecordhelper.error.ErrorMessage.*;
import static com.krystiankowalik.sportrecordhelper.logic.parser.athlete.AthleteParser.MIN_SINGLE_ATHLETE_LINE;

@Component
public final class AthleteValidator {

    private final Logger logger = Logger.getLogger(this.getClass());

    public boolean isValidAthlete(List<String> singleAthleteLines) {
        if (singleAthleteLines == null) {
            logger.error(EMPTY_LINE + " Nothing to parse from");
            return false;
        }
        if (singleAthleteLines.stream().allMatch(l -> l.equals(""))) {
            logger.error(EMPTY_LINE + " Nothing to parse from");
            return false;
        }

        if (singleAthleteLines.size() < MIN_SINGLE_ATHLETE_LINE) {
            logger.error(INVALID_MEMBER_COUNT + "in: " + singleAthleteLines + " expected >" + MIN_SINGLE_ATHLETE_LINE + " actual: " + singleAthleteLines.size());
            return false;
        }

        String name = singleAthleteLines.get(0);
        String country = singleAthleteLines.get(1);

        if (!isValidName(name)) {
            logger.error(INVALID_NAME + name);
            return false;
        }

        if (!isValidCountry(country)) {
            logger.error(INVALID_COUNTRY + country);
            return false;
        }

        return true;
    }

    private boolean isValidName(String string) {
        return string != null && string.matches("^[\\p{L}\\s'.-]+$");
    }

    private boolean isValidCountry(String string) {
        return string != null && string.matches("^[\\p{L}\\s'.-]+$");

    }

}
