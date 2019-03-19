package jrxml.jrxml.templates;

import jrxml.jrxml.filesManipulator.FilesGetter;
import jrxml.jrxml.reportsDesign.ReportDesign;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Template {


    private Map<String, JasperDesign> mapingFilesForTemplates(List<File> fileList) {
        ReportDesign reportDesign = new ReportDesign();
        Map<String, JasperDesign> templateMap = new HashMap<>();

        fileList.forEach(file -> {
            String name = file.getName().split("\\.")[0] + "Template.jrxml";
            try {
                JasperDesign templateDesign = reportDesign.createReport(file);
                templateMap.put(name, templateDesign);
            } catch (JRException e) {
                e.printStackTrace();
            }

        });

        return templateMap;
    }

    public void generateTemplates() {
        FilesGetter filesGetter = new FilesGetter("/home/strudla/Toma/workspace/papa/src/main/java/rs/unicreditbank/papa/entity");
        List<File> fileList = filesGetter.getFiles();
        Map<String, JasperDesign> templates = mapingFilesForTemplates(fileList);
        GenerateTemplates.generateTemplates(templates, "/home/strudla/Toma/Java/");

    }
}
