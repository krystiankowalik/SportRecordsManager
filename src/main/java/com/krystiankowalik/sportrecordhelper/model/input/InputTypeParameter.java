package com.krystiankowalik.sportrecordhelper.model.input;

import java.util.Arrays;

public enum InputTypeParameter {


    READ_FILE("-f"),
    READ_DIRECTORY("-d"),
    HELP("-h"),
    NO_INPUT("");

    public String inputType;

    InputTypeParameter(String inputType) {
        this.inputType = inputType;
    }

    public static InputTypeParameter getInputTypeByParameter(String parameter) {
        return Arrays.stream(values())
                .filter(v -> v.inputType.equals(parameter))
                .findFirst()
                .orElse(NO_INPUT);
    }


}
