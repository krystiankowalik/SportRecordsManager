package com.krystiankowalik.sportrecordhelper;

public class MainApp {

    public static void main(String[] args) {

        Control control = new Control();

        switch (args.length) {
            case 2:
                control.run(args[0], args[1]);
                break;
            case 1:
                control.run(args[0], "");
            case 0:
                control.run("", "");
            default:
                System.out.println("Wrong number of parameters: " + args.length);
        }

    }
}
