package jrxml.jrxml.filesManipulator;

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
        List<String> imports = new ArrayList<>();
        List<List<String>> fields = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> {
                if (s.contains("import")) {
                    String line = s.trim().split(" ")[1];
                    imports.add(line);

                }
                if (s.contains("private") && !s.contains("static")) {
                    String[] line = s.trim().split(";")[0].split(" ");
                    int last = line.length - 1;
                    List<String> field = new ArrayList(Arrays.asList(line[last], "java.lang.String"));
                    fields.add(field);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        ///ToDo Avoid nested loops
//        for (List field : fields) {
//            for (String i : imports) {
//                if (i.contains(field.get(1).toString())) {
//                    field.set(1, i.split(";")[0]);
//                    break;
//                }
//            }
//
//            String typeString = field.get(1).toString();
//            if (!typeString.contains(".") && !typeString.toLowerCase().contains("entity")) {
//                String fieldValue = "java.lang." + field.get(1).toString();
//                field.set(1, fieldValue);
//            }
//
//        }

        return fields;
    }


}
