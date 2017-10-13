package com.krystiankowalik.sportrecordhelper;


import com.krystiankowalik.sportrecordhelper.logic.io.FileHelper;
import com.krystiankowalik.sportrecordhelper.logic.io.StreamFileHelper;
import com.krystiankowalik.sportrecordhelper.logic.parser.AthleteParser;
import com.krystiankowalik.sportrecordhelper.logic.parser.IterativeAthleteParser;
import com.krystiankowalik.sportrecordhelper.model.athlete.Athletes;
import com.krystiankowalik.sportrecordhelper.model.input.InputTypeParameter;

import java.io.File;
import java.util.List;

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
                readFile(source);
                break;
            case READ_DIRECTORY:
                readDirectory(source);
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

    private void readFile(File sourceFile) {
        System.out.println("Reading file: " + sourceFile);

        if (sourceFile.exists()) {


            List<String> lines = fileHelper.readAllLines(sourceFile);

            /*

            System.out.println(athleteParser.parseAthletes(lines));


            System.out.println(System.lineSeparator());
            //System.out.println(athleteParser.parseAthletes().getAthletes().findFirst());

            //List<Athlete> athletes = athleteParser.parseAthletes(lines).getAthletes().collect(Collectors.toList());

            //athletes.sort((a1, a2) -> a1.compareTo(a2));

            //System.out.println(athletes);

            System.out.println(System.lineSeparator());
*/


            Athletes athletes =athleteParser.parseAthletes(lines);

            athletes.printAllThousandMetersTimes();
            athletes.printFirstRecordDate();
            athletes.printTopCountries();
            //System.out.println(athletes.stream().map(a->a.getRecords()).collect(Collectors.toList()).stream().sorted().collect(Collectors.toList()));
//        System.out.println(athletes.get(0).getRecords());

        } else {
            System.out.println("The source file: " + sourceFile + " doesn't exist.");
        }
    }

    private void readDirectory(File directory) {
        System.out.println("Reading dir...");
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


}
