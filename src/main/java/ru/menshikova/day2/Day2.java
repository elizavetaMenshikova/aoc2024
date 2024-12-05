package ru.menshikova.day2;

import ru.menshikova.utils.FileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class Day2 {
    private static final String SEPARATOR = " ";
    public static final String DIR_NAME = "day2";
    public static final String FILE_NAME = "ex";
    public static final int MAX_DIFF = 3;
    public static final int MIN_DIFF = 1;

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> text = FileReader.getText(DIR_NAME, FILE_NAME);

        long secureLinesCount = text.stream()
                .map(line -> Arrays.stream(line.split(SEPARATOR))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .filter(Day2::isLineSecure)
                .count();

        System.out.println(secureLinesCount);
    }

    private static boolean isLineSecure(int[] line) {
        return isInRange(line) && (isAscending(line) || isDescending(line));
    }

    private static boolean isAscending(int[] line) {
        for (int i = 0; i < line.length - 1; i++) {
            if (line[i] > line[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDescending(int[] line) {
        for (int i = 0; i < line.length - 1; i++) {
            if (line[i] < line[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isInRange(int[] line) {
        for (int i = 0; i < line.length - 1; i++) {
            int diff = Math.abs(line[i] - line[i + 1]);
            if (diff < MIN_DIFF || diff > MAX_DIFF) {
                return false;
            }
        }
        return true;
    }
}
