package hu.nive.ujratervezes.kepesitovizsga.digits;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Digits {
    public List<String> getNumbers() {
        List<String> result = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            if (i < 5) {
                result.add("" + i + (i + 5));
            } else {
                result.add("" + i + (i - 5));
            }
        }
        return result;
    }

    public Long getNumbers2() {
         return Stream.iterate(10, n -> n + 1)
                .limit(90)
                .filter(this::isFiveDiff)
                .count();
    }

    private boolean isFiveDiff(Integer i) {
        return i / 10 + 5 == i % 10 || i / 10 - 5 == i % 10;
    }
}
