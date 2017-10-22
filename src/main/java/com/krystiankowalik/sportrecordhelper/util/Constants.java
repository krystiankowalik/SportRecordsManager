package com.krystiankowalik.sportrecordhelper.util;

public class Constants {

    public static final String EMPTY = "";

    public static final String READ_FILE = "-f";
    public static final String READ_DIRECTORY = "-d";
    public static final String HELP = "-h";
    public static final String HELP_VERBOSE = "--getHelpContents";


    public static String getHelpContents() {
        StringBuilder sb = new StringBuilder();

        sb.append("In order to read files, use the following syntax:");
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
        sb.append("| -option | source |");
        sb.append(System.lineSeparator());
        sb.append("| -f | path to file | - to read a single file");
        sb.append(System.lineSeparator());
        sb.append("| -d | path to directory | - to read a all files in the directory");
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
        sb.append("for example: -f dane.txt");
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());

        sb.append("Enjoy :)");

        return sb.toString();
    }

    private Constants() {
    }

}
