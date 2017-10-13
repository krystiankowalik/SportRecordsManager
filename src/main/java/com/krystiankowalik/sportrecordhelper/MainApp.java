package com.krystiankowalik.sportrecordhelper;

import static com.krystiankowalik.sportrecordhelper.model.input.InputTypeParameter.NO_INPUT;
import static com.krystiankowalik.sportrecordhelper.model.input.InputTypeParameter.getInputTypeByParameter;

public class MainApp {

    public static void main(String[] args) {

        Control control = null;

        switch (args.length) {
            case 2:
                control = new Control(getInputTypeByParameter(args[0]), args[1]);
                break;
            case 1:
                control = new Control(getInputTypeByParameter(args[0]), NO_INPUT.inputType);
                break;
            case 0:
                control = new Control(NO_INPUT, NO_INPUT.inputType);
                break;
            default:
                System.out.println("Wrong number of parameters.");
        }

        assert control != null;
        control.run();


    }
}
