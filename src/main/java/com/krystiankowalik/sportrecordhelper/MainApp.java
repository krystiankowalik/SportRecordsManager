package com.krystiankowalik.sportrecordhelper;

import com.krystiankowalik.sportrecordhelper.model.input.InputParameters;

public class MainApp {

    public static void main(String[] args) {

        Control control = new Control(new InputParameters(args));

        control.run();
/*
        switch (args.length) {
            case 2:
                control = new Control(getOptionByParameter(args[0]), args[1]);
                break;
            case 1:
                control = new Control(getOptionByParameter(args[0]), NO_INPUT.inputType);
                break;
            case 0:
                control = new Control(NO_INPUT, NO_INPUT.inputType);
                break;
            default:
                System.out.println("Wrong number of parameters.");
        }

        assert control != null;
        control.run();*/


    }
}
