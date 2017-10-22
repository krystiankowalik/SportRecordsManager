package com.krystiankowalik.sportrecordhelper.logic.parser.validator;

import com.krystiankowalik.sportrecordhelper.logic.parser.error.ErrorMessage;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public final class AthleteValidator {

    private final Logger logger = Logger.getLogger(this.getClass());

    public boolean isValidAthlete(String name, String country) {
        if (!isValidName(name)) {
            logger.error(ErrorMessage.INVALID_NAME + name);
            return false;
        }

        if (!isValidCountry(country)) {
            logger.error(ErrorMessage.INVALID_COUNTRY + country);
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
