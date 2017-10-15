package com.krystiankowalik.sportrecordhelper;

import com.krystiankowalik.sportrecordhelper.model.input.InputParameters;

public class MainApp {

    public static void main(String[] args) {

        Control control = new Control(new InputParameters(args));

        control.run();

    }
}
