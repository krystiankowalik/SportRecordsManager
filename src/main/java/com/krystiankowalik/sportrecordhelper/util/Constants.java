package com.krystiankowalik.sportrecordhelper.util;

public final class Constants {

    public static final int BEFORE = -1;
    public static final int EQUAL = 0;
    public static final int AFTER = 1;

    public static final String EMPTY = "";

    public static final String DATA_FILE_EXTENSION = "txt";

    public static final String HELP_PROMPT = "Type: -h or --help to display user's manual";

    public static String help() {
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
