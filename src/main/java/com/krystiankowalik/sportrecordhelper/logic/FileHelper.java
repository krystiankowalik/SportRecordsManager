package com.krystiankowalik.sportrecordhelper.logic;

import com.krystiankowalik.sportrecordhelper.model.Athlete;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.krystiankowalik.sportrecordhelper.logic.AthleteParser.ATHLETE_DELIMITER;

@AllArgsConstructor
public class FileHelper {

    private String path;

    public List<Athlete> read() {
        File file = new File(path);
        if (file.isFile()) {
            return readFile(file.getPath());
        }
        return readDirectory(file.getPath());
    }

    private List<Athlete> readFile(String filePath) {

        List<String> singleAthleteLines = new ArrayList<>();
        List<Athlete> athletes = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {

            stream.forEach(line -> {
                if (!line.trim().equals(ATHLETE_DELIMITER)) {
                    singleAthleteLines.add(line.trim());
                } else {
                    athletes.add(new AthleteParser(singleAthleteLines).parseAthlete());
                    singleAthleteLines.clear();
                }
            });
            athletes.add(new AthleteParser(singleAthleteLines).parseAthlete());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return athletes;

    }

    private List<Athlete> readDirectory(String directoryPath) {

        List<Athlete> athletes = new ArrayList<>();

        try (Stream<Path> list = Files
                .list(Paths.get("."))
                .filter(Files::isRegularFile)) {

            list.forEach(file ->
                    athletes.addAll(readFile(file.toAbsolutePath().toString())));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return athletes;
    }

}
