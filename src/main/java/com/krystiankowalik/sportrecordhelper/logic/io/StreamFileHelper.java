package com.krystiankowalik.sportrecordhelper.logic.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    @Override
    public List<File> getAllFilesInDirectory(File directory) {
        List<File> files = null;
        try {
            files = Files
                    .list(directory.toPath())
                    .map(Path::toFile)
                    .filter(f -> f.isFile() && f.toString().endsWith("txt"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }
}
