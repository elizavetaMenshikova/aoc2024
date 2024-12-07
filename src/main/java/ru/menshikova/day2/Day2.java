package ru.menshikova.day2;

import ru.menshikova.utils.FileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day2 {
    private static final String SEPARATOR = " ";
    public static final String DIR_NAME = "day2";
    public static final String FILE_NAME = "ex";
    public static final int MAX_DIFF = 3;
    public static final int MIN_DIFF = 1;

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> text = FileReader.getText(DIR_NAME, FILE_NAME);

        List<int[]> unsecureLines = text.stream()
                .map(line -> Arrays.stream(line.split(SEPARATOR))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .filter(line -> !isLineSecure(line))
                .collect(Collectors.toList());

        long secureLinesCount = text.size() - unsecureLines.size();

        System.out.println(secureLinesCount);

        long mySolution = secureLinesCount;
        long chatGptSolution = secureLinesCount;

        for (int[] unsecureLine : unsecureLines) {
            for (int i = 0; i < unsecureLine.length; i++) {
                int[] truncatedUnsecureLine = new int[unsecureLine.length - 1];
                for (int j = 0, k = 0; j < unsecureLine.length; j++) {
                    if (j != i) {
                        truncatedUnsecureLine[k] = unsecureLine[j];
                        k++;
                    }
                }
                if (isLineSecure(truncatedUnsecureLine)) {
                    mySolution += 1;
                    break;
                }
            }
        }

        System.out.println(mySolution);

        chatGptSolution += unsecureLines.stream()
                .filter(unsecureLine ->
                        IntStream.range(0, unsecureLine.length)
                                .mapToObj(i -> IntStream.range(0, unsecureLine.length)
                                        .filter(j -> j != i)
                                        .map(j -> unsecureLine[j])
                                        .toArray())
                                .anyMatch(Day2::isLineSecure)
                )
                .count();

        System.out.println(chatGptSolution);
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
