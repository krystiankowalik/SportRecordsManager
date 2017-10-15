package com.krystiankowalik.sportrecordhelper;


import com.krystiankowalik.sportrecordhelper.logic.AthleteFileProvider;
import com.krystiankowalik.sportrecordhelper.logic.AthleteFileProviderImpl;
import com.krystiankowalik.sportrecordhelper.logic.io.StreamFileHelper;
import com.krystiankowalik.sportrecordhelper.logic.parser.IterativeAthleteParser;
import com.krystiankowalik.sportrecordhelper.model.athlete.Athletes;
import com.krystiankowalik.sportrecordhelper.model.input.InputParameters;
import com.krystiankowalik.sportrecordhelper.util.Constants;

import java.nio.file.Path;

public final class Control {

    private InputParameters inputParameters;

    private AthleteFileProvider athleteFileProvider;


    public Control(final InputParameters inputParameters) {
        this.inputParameters = inputParameters;
        this.athleteFileProvider = new AthleteFileProviderImpl(new StreamFileHelper(), new IterativeAthleteParser());
    }

    public void run() {

        if (inputParameters.isValid()) {

            switch (inputParameters.getOption()) {
                case READ_FILE:
                    printStatistics(readFile(inputParameters.getSource()));
                    break;
                case READ_DIRECTORY:
                    printStatistics(readDirectory(inputParameters.getSource()));
                    break;
                case HELP:
                    displayHelp();
                    break;
                case HELP_VERBOSE:
                    displayHelp();
                    break;
                case NO_INPUT:
                    break;

                default:
                    displayHelp();
            }
        }
    }

    private Athletes readFile(Path filePath) {
        return athleteFileProvider.readFile(filePath);

    }

    private Athletes readDirectory(Path directoryPath) {
        return athleteFileProvider.readDirectory(directoryPath);
    }

    private void displayHelp() {
        System.out.print(System.lineSeparator());

        System.out.println(Constants.help());
    }

    private void printStatistics(Athletes athletes) {

        if (!athletes.isEmpty()) {
            athletes.printAllThousandMetersTimes();
            athletes.printFirstRecordDate();
            athletes.printTopCountries(3);
        }

    }


}
