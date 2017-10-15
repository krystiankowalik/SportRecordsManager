package com.krystiankowalik.sportrecordhelper.model.input;

import java.util.Arrays;

public enum Option {


    READ_FILE("-f"),
    READ_DIRECTORY("-d"),
    HELP("-h"),
    HELP_VERBOSE("--help"),
    NO_INPUT("");

    public String inputType;

    Option(String inputType) {
        this.inputType = inputType;
    }

    public static Option getOptionByParameter(String parameter) {
        return Arrays.stream(values())
                .filter(v -> v.inputType.equals(parameter))
                .findFirst()
                .orElse(NO_INPUT);
    }


}
