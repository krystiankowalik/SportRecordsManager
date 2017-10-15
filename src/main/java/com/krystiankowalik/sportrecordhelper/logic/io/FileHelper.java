package com.krystiankowalik.sportrecordhelper.logic.io;

import java.nio.file.Path;
import java.util.List;

public interface FileHelper {
    List<String> readAllLines(Path filePath);

    List<Path> getAllFilesInDirectory(Path directory);
}
