package com.krystiankowalik.sportrecordhelper.logic.io;

import java.io.File;
import java.util.List;

public interface FileHelper {
    List<String> readAllLines(File filePath);

    List<File> getAllFilesInDirectory(File directory);
}
