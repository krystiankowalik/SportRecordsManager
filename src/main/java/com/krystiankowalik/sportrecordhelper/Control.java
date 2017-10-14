package com.krystiankowalik.sportrecordhelper;


import com.krystiankowalik.sportrecordhelper.logic.io.FileHelper;
import com.krystiankowalik.sportrecordhelper.logic.io.StreamFileHelper;
import com.krystiankowalik.sportrecordhelper.logic.parser.AthleteParser;
import com.krystiankowalik.sportrecordhelper.logic.parser.IterativeAthleteParser;
import com.krystiankowalik.sportrecordhelper.model.athlete.Athletes;
import com.krystiankowalik.sportrecordhelper.model.input.InputTypeParameter;

import java.io.File;
import java.util.List;
import java.util.TreeSet;

public final class Control {

    private FileHelper fileHelper;

    private InputTypeParameter inputTypeParameter;
    private File source;
    private AthleteParser athleteParser;


    public Control(InputTypeParameter inputTypeParameter, String inputSourceParameter) {
        this.inputTypeParameter = inputTypeParameter;
        this.source = new File(inputSourceParameter);
        this.fileHelper = new StreamFileHelper();
        athleteParser = new IterativeAthleteParser();
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

    private Athletes readFile(File sourceFile) {
        System.out.println("Reading file: " + sourceFile);

        Athletes athletes = null;


        if (sourceFile.exists()) {

            List<String> lines = fileHelper.readAllLines(sourceFile);

            athletes = athleteParser.parseAthletes(lines);


        } else {
            System.out.println("The source file: " + sourceFile + " doesn't exist.");
        }

        return athletes;
    }

    private Athletes readDirectory(File directory) {
        System.out.println("Reading dir...");

        Athletes athletes = new Athletes(new TreeSet<>());

        fileHelper
                .getAllFilesInDirectory(directory)
                .forEach(f -> athletes.addAll(readFile(f)));

        return athletes;

    }

    private void displayHelp() {
        System.out.println();

        StringBuilder sb = new StringBuilder();

        sb.append("Welcome to SportRecordHelper.");
        sb.append(System.lineSeparator());
        sb.append("In order to read files, the following syntax:");
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
        sb.append("| -f | path to file | - to read a single file");
        sb.append(System.lineSeparator());
        sb.append("| -d | path to directory | - to read a all files in the directory");
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());

        sb.append("Enjoy :)");

        System.out.println(sb.toString());
    }

    private void printStatistics(Athletes athletes) {
        athletes.printAllThousandMetersTimes();
        athletes.printFirstRecordDate();
        athletes.printTopCountries(3);
    }


}
