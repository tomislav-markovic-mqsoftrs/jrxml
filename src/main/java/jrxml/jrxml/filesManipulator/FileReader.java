package jrxml.jrxml.filesManipulator;

import jrxml.jrxml.Consts;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FileReader {

    public static List<List<String>> readLineByLine(String filePath) {
        List<List<String>> fields = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> {

                if (Consts.fileReaderCondition(s)) {
                    String[] line = s.trim().split(";")[0].split(" ");
                    int last = line.length - 1;
                    List<String> field = new ArrayList(Arrays.asList(line[last], "java.lang.String"));
                    fields.add(field);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        return fields;
    }


}
