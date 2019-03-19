package jrxml.jrxml;

import java.io.File;

public class Consts {
    public static String dtoFilesPath = "/home/strudla/Toma/workspace/papa/src/main/java/rs/unicreditbank/papa/dto/response";
    public static String exportTemplatesPath = "/home/strudla/Toma/";
    public static String fileNameCreator(File file){
        return file.getName().split("\\.")[0] + "Template.jrxml";

    }


    public static String encoding = "UTF-8";
}
