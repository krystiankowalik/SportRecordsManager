package com.krystiankowalik.sportrecordhelper;


import com.krystiankowalik.sportrecordhelper.logic.AthleteParser;
import com.krystiankowalik.sportrecordhelper.logic.FileHelper;
import com.krystiankowalik.sportrecordhelper.model.athlete.Athlete;

import java.util.List;
import java.util.stream.Collectors;

public class Control {

    public static final String READ_FILE = "-f";
    public static final String READ_DIRECTORY = "-d";
    public static final String HELP = "-h";
    public static final String NO_INPUT = "";

    public void run(String inputTypeParameter, String inputSourceParameter) {

        switch (inputTypeParameter) {
            case READ_FILE:
                readFile(inputSourceParameter);
                break;
            case READ_DIRECTORY:
                readDirectory(inputSourceParameter);
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

    private void readFile(String inputSourceParameter) {
        System.out.println("Reading file: " + inputSourceParameter);

        List<String> lines = FileHelper.readAllLines(inputSourceParameter);

        AthleteParser athleteParser = new AthleteParser(lines);

        System.out.println(athleteParser.parseAthletes());


        System.out.println(System.lineSeparator());
        //System.out.println(athleteParser.parseAthletes().getAthletes().findFirst());

        List<Athlete> athletes = athleteParser.parseAthletes().getAthletes().collect(Collectors.toList());

        athletes.sort((a1,a2)->a1.compareTo(a2));

        System.out.println(athletes);

        System.out.println(System.lineSeparator());

        //System.out.println(athletes.stream().map(a->a.getRecords()).collect(Collectors.toList()).stream().sorted().collect(Collectors.toList()));
//        System.out.println(athletes.get(0).getRecords());


    }

    private void readDirectory(String inputSourceParameter) {
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
