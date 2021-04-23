package hu.nive.ujratervezes.kepesitovizsga.finelongword;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class FineLongWord {
    public char[] readFineLongWordFromFileAndGetItInAnArray(String s) {
        char[] result = new char[50];
        try (BufferedReader bf = Files.newBufferedReader(Path.of("src/main/resources/" + s))) {
            String line = bf.readLine();
            int index = 0;
            int sub = line.length() - 1;
            do {
                for (Character item : line.toCharArray()) {
                    result[index] = item;
                    index++;
                }
                index -= sub;
            } while ((line = bf.readLine()) != null);
            char[] cp = Arrays.copyOfRange(result, 0, index + sub);
            return cp;
        } catch (IOException e) {
            throw new IllegalStateException("file error", e);
        }
    }

    public char[] readFineLongWordFromFileAndGetItInAnArray2(String s) {
        try (BufferedReader bf = Files.newBufferedReader(Path.of("src/main/resources/" + s))) {
            StringBuilder sb = new StringBuilder();
            sb.append(bf.readLine());
            String line;
            while ((line = bf.readLine()) != null) {
                sb.append(line.charAt(line.length() - 1));
            }
            return sb.toString().toCharArray();

        } catch (IOException e) {
            throw new IllegalStateException("file error", e);
        }
    }

}
