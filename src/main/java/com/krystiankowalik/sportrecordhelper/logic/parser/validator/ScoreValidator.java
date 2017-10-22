package com.krystiankowalik.sportrecordhelper.logic.parser.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static com.krystiankowalik.sportrecordhelper.logic.parser.athlete.AthleteParser.SCORE_DELIMITER;
import static com.krystiankowalik.sportrecordhelper.logic.parser.athlete.AthleteParser.SCORE_MEMBERS_COUNT;
import static com.krystiankowalik.sportrecordhelper.error.ErrorMessage.*;

@Component
public final class ScoreValidator {

    private final Logger logger = Logger.getLogger(this.getClass());

    public boolean isValidRecordLine(String string) {

        if (!isPipeDelimited(string)) {
            logger.error(MISSING_DELIMITER + "in: " + string);
            return false;
        }

        final String[] splitLine = string.split(SCORE_DELIMITER);

        if (!isValidMembersCount(splitLine, SCORE_MEMBERS_COUNT)) {
            logger.error(INVALID_MEMBER_COUNT + ": " + splitLine.length + " in: " + string + " expected count: " + SCORE_MEMBERS_COUNT);
            return false;
        }

        if (!isValidDateFormat(splitLine[0])) {
            logger.error(INVALID_DATE_FORMAT + "in " + splitLine[0]);
            return false;
        }

        if (!isInteger(splitLine[1])) {
            logger.error(NOT_AN_INTEGER + ": " + splitLine[1]);
            return false;
        }

        if (!isNumber(splitLine[2])) {
            logger.error(NOT_A_DECIMAL + ": " + splitLine[2]);
            return false;
        }

        return true;
    }

    private boolean isValidMembersCount(String[] splitRecordLine, int recordMembersCount) {
        return splitRecordLine != null && splitRecordLine.length == recordMembersCount;
    }

    private boolean isPipeDelimited(String string) {
        return string != null && string.matches("(.*" + SCORE_DELIMITER + ".*){2}");
    }

    private boolean isValidDateFormat(String string) {
        if (string == null) {
            return false;
        }
        try {
            LocalDate.parse(string);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean isInteger(String string) {
        return string != null && string.matches("^[1-9]\\d+$");
    }

    private boolean isNumber(String string) {
        return string != null && string.matches("^[1-9]\\d*(\\.\\d+)?$");
    }


}
