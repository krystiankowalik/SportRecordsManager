package com.krystiankowalik.sportrecordhelper.model.input;

import com.krystiankowalik.sportrecordhelper.model.error.Error;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static com.krystiankowalik.sportrecordhelper.model.input.Option.*;
import static com.krystiankowalik.sportrecordhelper.util.Constants.EMPTY;

public class InputParameters {

    private List<String> inputParameters;

    private Option option;
    private Path source;

    private boolean valid;

    public InputParameters(String[] input) {
        this.inputParameters = Arrays.asList(input);
        this.option = NO_INPUT;
        this.source = Paths.get(EMPTY);
        this.valid = true;
        initParams();
    }

    private void initParams() {

        final String optionParam = readParameter(inputParameters, 0);
        final String sourceParam = readParameter(inputParameters, 1);

        option = getOptionByParameter(optionParam);
        source = Paths.get(sourceParam);

        switch (inputParameters.size()) {

            case 2:
                if (option.equals(NO_INPUT)) {
                    registerInvalidParamError(optionParam);
                } else if (sourceParam.equals(EMPTY)) {
                    registerInvalidParamError(sourceParam);
                }
                break;
            case 1:
                if (!option.equals(HELP) && !option.equals(HELP_VERBOSE) &&
                        !option.equals(READ_FILE) && !option.equals(READ_DIRECTORY)) {
                    registerInvalidParamError(optionParam);
                }
                break;
            default:
                Error.print(Error.WRONG_PARAM_COUNT, inputParameters.size(), true);
                valid = false;
        }
    }

    private String readParameter(List<String> inputParametersList, int index) {

        if (inputParametersList.size() - 1 >= index) {
            return inputParametersList.get(index);
        } else {
            return EMPTY;
        }

    }

    private void registerInvalidParamError(String param) {
        Error.print(Error.INVALID_PARAM, param, true);
        valid = false;
    }

    public boolean isValid() {
        return valid;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public Path getSource() {
        return source;
    }

    public void setSource(Path source) {
        this.source = source;
    }
}
