package com.krystiankowalik.sportrecordhelper.logic;

import com.krystiankowalik.sportrecordhelper.model.athlete.Athletes;

import java.nio.file.Path;

public interface AthleteFileProvider {


    Athletes readFile(Path file);

    Athletes readDirectory(Path directory);


}