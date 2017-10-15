package com.krystiankowalik.sportrecordhelper.logic.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StreamFileHelper implements FileHelper {

    @Override
    public List<String> readAllLines(Path filePath) {

        List<String> lines = null;

        try (Stream<String> linesStream = Files.lines(filePath)) {
            lines = linesStream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;

    }

    @Override
    public List<Path> getAllFilesInDirectory(Path directoryPath) {
        List<Path> files = null;
        try {
            files = Files
                    .list(directoryPath)
                    //To generify!!!
                    .filter(f -> Files.isRegularFile(f))// && f.toString().endsWith(Constants.DATA_FILE_EXTENSION))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }
}
