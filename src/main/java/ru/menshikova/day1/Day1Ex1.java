package ru.menshikova.day1;

import ru.menshikova.utils.FileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day1Ex1 {
    private static final String SEPARATOR = "   ";

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> text = FileReader.getText("day1", "ex1");

        int result = 0;

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        text.stream()
                .map(s -> s.split(SEPARATOR))
                .forEach((array) -> {
                    left.add(Integer.parseInt(array[0]));
                    right.add(Integer.parseInt(array[1]));
                });
        left.sort(Comparator.comparingInt(a -> a));
        right.sort(Comparator.comparingInt(a -> a));

        for (int i = 0; i < left.size(); i++) {
            result += Math.abs(left.get(i) - right.get(i));
        }

        System.out.println(result);
    }
}
