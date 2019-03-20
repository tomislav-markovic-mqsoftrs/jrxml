package jrxml.jrxml.filesManipulator;

import jrxml.jrxml.Consts;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FilesGetter {
    private String filesPath;


    public FilesGetter(String filesPath) {
        this.filesPath = filesPath;

    }

    public String getFilesPath() {
        return filesPath;
    }

    public void setFilesPath(String filesPath) {
        this.filesPath = filesPath;
    }



    public List<File> getFiles(){
        List<File> files = new ArrayList<>();
        File folder = new File(filesPath);
        File[] listOfFiles = folder.listFiles();
        for(File file : listOfFiles){
            if(Consts.fileGetterCondition(file.getName())){
                files.add(file);
            }
        }
        return files;
    }










}
