package com.krystiankowalik.sportrecordhelper.model.error;

import com.krystiankowalik.sportrecordhelper.util.Constants;

public enum Error {


    WRONG_PARAM_COUNT("Wrong number of parameters"),
    INVALID_PARAM("Invalid parameter"),
    NO_SUCH_DIRECTORY("No such directory"),
    NO_SUCH_FILE("No such file"),
    NOT_A_FILE("Not a file"),
    NOT_A_DIRECTORY("Not a directory");

    private String errorMessage;

    Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static void print(Error error) {
        System.out.println("Error: " + error.errorMessage);
        System.out.println(Constants.HELP_PROMPT);
    }

    public static void print(Error error, String customMessage) {
        System.out.println("Error: " + error.errorMessage + ": " + customMessage);
        System.out.println(Constants.HELP_PROMPT);
    }

    public static void print(Error error, int customMessage) {
        System.out.println("Error: " + error.errorMessage + ": " + customMessage);
        System.out.println(Constants.HELP_PROMPT);
    }
}

