package jrxml.jrxml.templates;

import jrxml.jrxml.Consts;
import jrxml.jrxml.filesManipulator.FilesGetter;
import jrxml.jrxml.reportsDesign.ReportDesign;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;

import java.io.File;
import java.lang.reflect.Array;
import java.util.*;

public class Template {


    private Map<String, JasperDesign> mapingFilesForTemplates(List<File> fileList) {
        ReportDesign reportDesign = new ReportDesign();
        Map<String, JasperDesign> templateMap = new HashMap<>();

        fileList.forEach(file -> {
           String name = Consts.fileNameCreator(file);
            try {
                JasperDesign templateDesign = reportDesign.createReport(file);
                templateMap.put(name, templateDesign);
            } catch (JRException e) {
                e.printStackTrace();
            }

        });

        return templateMap;
    }

    public void generateSingleTemplate(){
        File file = new File(Consts.dtoFilesPath+"/" + Consts.singleDtoFile);
        List<File> files = new ArrayList<>(Arrays.asList(file));
        Map<String, JasperDesign> templates = mapingFilesForTemplates(files);
        GenerateTemplates.generateTemplates(templates, Consts.exportTemplatesPath);
    }

    public void generateTemplates() {

        FilesGetter filesGetter = new FilesGetter(Consts.dtoFilesPath);
        List<File> fileList = filesGetter.getFiles();
        Map<String, JasperDesign> templates = mapingFilesForTemplates(fileList);
        GenerateTemplates.generateTemplates(templates, Consts.exportTemplatesPath);

    }
}
