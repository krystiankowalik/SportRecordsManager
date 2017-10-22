package com.krystiankowalik.sportrecordhelper.logic.io;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.krystiankowalik.sportrecordhelper.logic.parser.error.ErrorMessage.*;

@Service
public final class StreamFileHelper implements FileHelper {

    private final Logger logger = Logger.getLogger(this.getClass());


    @Override
    public List<String> readAllLines(Path filePath) {

        List<String> lines = null;

        if (!Files.exists(filePath)) {
            logger.error(NO_SUCH_FILE + filePath.toAbsolutePath().toString());
        } else if (!Files.isRegularFile(filePath)) {
            logger.error(NOT_A_FILE + filePath.toAbsolutePath().toString());
        } else {

            try (Stream<String> linesStream = Files.lines(filePath, Charset.forName("UTF-8"))) {
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
            logger.error(NO_SUCH_DIRECTORY + directoryPath.toAbsolutePath().toString());
        } else if (!Files.isDirectory(directoryPath)) {
            logger.error(NOT_A_DIRECTORY + directoryPath.toAbsolutePath().toString());
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
