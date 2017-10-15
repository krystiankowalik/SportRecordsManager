package com.krystiankowalik.sportrecordhelper.logic.parser.validator;

import com.krystiankowalik.sportrecordhelper.model.error.Error;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static com.krystiankowalik.sportrecordhelper.logic.parser.AthleteParser.RECORD_DELIMITER;
import static com.krystiankowalik.sportrecordhelper.logic.parser.AthleteParser.RECORD_MEMBERS_COUNT;

public final class RecordValidator {


    public boolean isValidRecordLine(String string) {

        if (!isPipeDelimited(string)) {
            Error.print(Error.MISSING_DELIMITER, "in: " + string,false);
            return false;
        }

        final String[] splitLine = string.split(RECORD_DELIMITER);

        if (!isValidMembersCount(splitLine, RECORD_MEMBERS_COUNT)) {
            Error.print(Error.INVALID_MEMBER_COUNT, ": " + splitLine.length + "for token: " + string + " expected count: " + RECORD_MEMBERS_COUNT,false);
            return false;
        }

        if (!isValidDateFormat(splitLine[0])) {
            Error.print(Error.INVALID_DATE_FORMAT, "in: " + string,false);
            return false;
        }

        if (!isInteger(splitLine[1])) {
            Error.print(Error.NOT_AN_INTEGER, string,false);
            return false;
        }

        if (!isNumber(splitLine[2])) {
            Error.print(Error.NOT_A_DECIMAL, string,false);
            return false;
        }

        return true;
    }

    private boolean isValidMembersCount(String[] splitRecordLine, int recordMembersCount) {
        return splitRecordLine.length == recordMembersCount;
    }

    private boolean isPipeDelimited(String string) {
        return string.matches("(.*" + RECORD_DELIMITER + ".*){2}");
    }

    private boolean isValidDateFormat(String string) {
        try {
            LocalDate.parse(string);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean isInteger(String string) {
        return string.matches("^[1-9]\\d+$");
    }

    private boolean isNumber(String string) {
        return string.matches("^[1-9]\\d*(\\.\\d+)?$");
    }


}
