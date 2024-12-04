package ru.menshikova.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class FileReader {
    private static final String SEPARATOR = "/";
    private static final String FILE_EXTENSION = ".txt";

    public static List<String> getText(String dir, String fileName) throws IOException, URISyntaxException {
        Path filePath = Path.of(Objects.requireNonNull(Thread.currentThread()
                        .getContextClassLoader()
                        .getResource(dir + SEPARATOR + fileName + FILE_EXTENSION))
                .toURI());
        return Files.readAllLines(filePath);
    }
}
