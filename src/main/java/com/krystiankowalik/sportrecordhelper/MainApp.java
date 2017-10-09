package com.krystiankowalik.sportrecordhelper;

import com.krystiankowalik.sportrecordhelper.logic.FileHelper;

public class MainApp {

    public static void main(String[] args) {

        System.out.println(new FileHelper("/home/wd40/IdeaProjects/Many-to-Many sample/sportrecordhelper/src/main/resources/dane.txt").read());
        //System.out.println(new FileHelper().readFile(new File("dane.txt")));
    }
}
