package com.krystiankowalik.sportrecordhelper.logic.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StreamFileHelper implements FileHelper {

    @Override
    public List<String> readAllLines(File file) {

        List<String> lines = null;

        try (Stream<String> linesStream = Files.lines(file.toPath())) {
            lines = linesStream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;

    }
}
