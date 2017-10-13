package com.krystiankowalik.sportrecordhelper.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class FileHelper {

    public static List<String> readAllLines(String filePath) {

        List<String> lines = null;

        try (Stream<String> linesStream = Files.lines(Paths.get(filePath))) {
            lines = linesStream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;

    }
}
