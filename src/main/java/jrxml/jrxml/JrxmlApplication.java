package jrxml.jrxml;

import jrxml.jrxml.filesManipulator.FileReader;
import jrxml.jrxml.filesManipulator.FilesGetter;
import jrxml.jrxml.reportsDesign.ReportDesign;
import jrxml.jrxml.templates.Template;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlWriter;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.*;

@SpringBootApplication
public class JrxmlApplication {
    private static final String FIELD_NAME = "aField";
    private static final String PARAMETER_DATA = "tableData";
    private JasperDesign jasperDesign;


    public static void main(String[] args) throws JRException {
        Template template = new Template();
        template.generateTemplates();

//        FilesGetter filesGetter = new FilesGetter("/home/strudla/Toma/workspace/papa/src/main/java/rs/unicreditbank/papa/entity");
//
//
//        //Trenutno ne radi
////        FilesControler filesControler = new FilesControler(filesGetter.getFiles());
//
//
//        List<File> fileList = filesGetter.getFiles();
//
//
//        List elements = FileReader.readLineByLine(fileList.get(2).getPath());
//
//        for (Object field : elements) {
//            System.out.println(field.toString());
//        }
//
//
//        ReportDesign tableReport = new ReportDesign();
//        List<File> files = new ArrayList(Arrays.asList(fileList.get(0), fileList.get(1)));
//        tableReport.createReports(files);
//
//
//        JasperDesign design = tableReport.createReport(fileList.get(2));
//        JRXmlWriter.writeReport(design, "/home/strudla/Toma/file.jrxml", "UTF-8");
//        JasperReport report = JasperCompileManager.compileReport(tableReport.jasperDesign);
//        Map<String, Object> params = new HashMap<>();
//        List<Map<String, Object>> data = TestData.tableData();
//        params.put(PARAMETER_DATA, data);
//
//        JasperPrint print = JasperFillManager.fillReport(report, params);
//        JasperViewer.viewReport(print);
//
//        SpringApplication.run(JrxmlApplication.class, args);
    }


}
