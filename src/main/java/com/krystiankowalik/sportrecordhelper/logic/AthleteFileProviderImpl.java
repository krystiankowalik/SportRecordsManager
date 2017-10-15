package com.krystiankowalik.sportrecordhelper.logic;

import com.krystiankowalik.sportrecordhelper.logic.io.FileHelper;
import com.krystiankowalik.sportrecordhelper.logic.parser.AthleteParser;
import com.krystiankowalik.sportrecordhelper.model.athlete.Athletes;
import com.krystiankowalik.sportrecordhelper.util.Constants;

import java.nio.file.Path;
import java.util.List;

public class AthleteFileProviderImpl implements AthleteFileProvider {

    private FileHelper fileHelper;
    private AthleteParser athleteParser;

    public AthleteFileProviderImpl(FileHelper fileHelper, AthleteParser athleteParser) {
        this.fileHelper = fileHelper;
        this.athleteParser = athleteParser;
    }

    @Override
    public Athletes readFile(Path filePath) {
        return athleteParser.parseAthletes(fileHelper.readAllLines(filePath));
    }

    @Override
    public Athletes readDirectory(Path directoryPath) {

        Athletes athletes = new Athletes();

        List<Path> allFilesInDirectory = fileHelper.getAllFilesInDirectory(directoryPath);
        if (allFilesInDirectory != null) {
            allFilesInDirectory
                    .stream()
                    .filter(filePath -> filePath.toString().endsWith(Constants.DATA_FILE_EXTENSION))
                    .forEach(filePath -> athletes.addAll(readFile(filePath)));
        }

        return athletes;
    }
}
