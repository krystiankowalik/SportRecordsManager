package com.krystiankowalik.sportrecordhelper;


import com.krystiankowalik.sportrecordhelper.logic.AthleteProvider;
import com.krystiankowalik.sportrecordhelper.logic.AthleteProviderImpl;
import com.krystiankowalik.sportrecordhelper.logic.io.StreamFileHelper;
import com.krystiankowalik.sportrecordhelper.logic.parser.IterativeAthleteParser;
import com.krystiankowalik.sportrecordhelper.model.athlete.Athletes;
import com.krystiankowalik.sportrecordhelper.model.input.InputTypeParameter;
import com.krystiankowalik.sportrecordhelper.util.Constants;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Control {

    private InputTypeParameter inputTypeParameter;
    private Path source;

    private AthleteProvider athleteProvider;


    public Control(InputTypeParameter inputTypeParameter, String inputSourceParameter) {
        this.inputTypeParameter = inputTypeParameter;
        this.source = Paths.get(inputSourceParameter);
        this.athleteProvider = new AthleteProviderImpl(new StreamFileHelper(), new IterativeAthleteParser());
    }

    public void run() {

        switch (inputTypeParameter) {
            case READ_FILE:
                printStatistics(readFile(source));
                break;
            case READ_DIRECTORY:
                printStatistics(readDirectory(source));
                break;
            case HELP:
                displayHelp();
                break;
            case NO_INPUT:
                displayHelp();
                break;

            default:
                System.out.println("Unknown input type parameter: " + inputTypeParameter);
        }
    }

    private Athletes readFile(Path filePath) {
        return athleteProvider.readFile(filePath);
    }

    private Athletes readDirectory(Path directoryPath) {
        return athleteProvider.readDirectory(directoryPath);
    }

    private void displayHelp() {
        System.out.println();

        System.out.println(Constants.help());
    }

    private void printStatistics(Athletes athletes) {
        athletes.printAllThousandMetersTimes();
        athletes.printFirstRecordDate();
        athletes.printTopCountries(3);
    }


}
