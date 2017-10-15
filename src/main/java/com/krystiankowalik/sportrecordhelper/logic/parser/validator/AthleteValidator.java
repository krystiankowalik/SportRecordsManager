package com.krystiankowalik.sportrecordhelper.logic.parser.validator;

import com.krystiankowalik.sportrecordhelper.model.error.Error;

public final class AthleteValidator {

    public boolean isValidAthlete(String name, String country){
        if(!isValidName(name)){
            Error.print(Error.INVALID_NAME, name, false);
            return false;
        }

        if (!isValidCountry(country)){
            Error.print(Error.INVALID_COUNTRY, name, false);
            return false;
        }

        return true;
    }

    private boolean isValidName(String string) {
        return string.matches("^[\\p{L}\\s'.-]+$");
    }

    private boolean isValidCountry(String string) {
        return string.matches("^[\\p{L}\\s'.-]+$");

    }

}
