package com.krystiankowalik.sportrecordhelper.logic.io;

import com.krystiankowalik.sportrecordhelper.model.error.Error;

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

        if (!Files.exists(filePath)) {
            Error.print(Error.NO_SUCH_FILE, filePath.toString());
        } else if (!Files.isRegularFile(filePath)) {
            Error.print(Error.NOT_A_FILE, filePath.toString());
        } else {

            try (Stream<String> linesStream = Files.lines(filePath)) {
                lines = linesStream.collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return lines;

    }

    @Override
    public List<Path> getAllFilesInDirectory(Path directoryPath) {
        List<Path> files = null;

        if (!Files.exists(directoryPath)) {
            Error.print(Error.NO_SUCH_DIRECTORY, directoryPath.toString());
        } else if (!Files.isDirectory(directoryPath)) {
            Error.print(Error.NOT_A_DIRECTORY, ": " + directoryPath);
        } else {

            try {
                files = Files
                        .list(directoryPath)
                        .filter(f -> Files.isRegularFile(f))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return files;
    }
}
