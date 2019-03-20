package jrxml.jrxml;

import java.io.File;

public class Consts {
    public static String dtoFilesPath = "/home/toma/Toma/workspace/papa/src/main/java/rs/unicreditbank/papa/dto/response";
    public static String singleDtoFile = "PayerResponseDTO.java";
    public static String exportTemplatesPath = "/home/toma/Toma/JasperTemplates/";
    public static String fileNameCreator(File file){
        return file.getName().split("\\.")[0] + "Template.jrxml";

    }

    public static Boolean fileReaderCondition(String s){
        return s.contains("private");
    }
    public static Boolean fileGetterCondition(String s) {
        return s.toLowerCase().contains("dto");
    }
    public static String encoding = "UTF-8";
}
