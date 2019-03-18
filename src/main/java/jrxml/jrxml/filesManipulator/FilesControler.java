package jrxml.jrxml.filesManipulator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilesControler {
    List<File> list = new ArrayList<>();


    public FilesControler(List<File> list) {
        this.list = list;
    }

    public List<File> getList() {
        return list;
    }

    public void setList(List<File> list) {
        this.list = list;
    }



}
