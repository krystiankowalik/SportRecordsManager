package com.krystiankowalik.sportrecordhelper.model.error;

import com.krystiankowalik.sportrecordhelper.util.Constants;

public enum Error {


    WRONG_PARAM_COUNT("Wrong number of parameters"),
    INVALID_PARAM("Invalid parameter"),

    NO_SUCH_DIRECTORY("No such directory"),
    NO_SUCH_FILE("No such file"),
    NOT_A_FILE("Not a file"),
    NOT_A_DIRECTORY("Not a directory"),

    MISSING_DELIMITER("Missing delimiter"),
    INVALID_MEMBER_COUNT("Invalid member count"),
    INVALID_DATE_FORMAT("Invalid date format"),
    NOT_AN_INTEGER("Not an integer"),
    NOT_A_DECIMAL("Not a decimal"),
    PARSING_ERROR("Parsing error"),

    INVALID_NAME("Invalid Name"),
    INVALID_COUNTRY("Invalid country");

    private String errorMessage;

    Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static void print(Error error, boolean displayHelpPrompt) {
        System.out.println("Error: " + error.errorMessage);
        if (displayHelpPrompt) {
            System.out.println(Constants.HELP_PROMPT);
        }
    }

    public static void print(Error error, String customMessage, boolean displayHelpPrompt) {
        System.out.println("Error: " + error.errorMessage + ": " + customMessage);
        if (displayHelpPrompt) {
            System.out.println(Constants.HELP_PROMPT);
        }
    }

    public static void print(Error error, int customMessage, boolean displayHelpPrompt) {
        print(error, Integer.toString(customMessage), displayHelpPrompt);
    }
}

