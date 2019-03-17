package jrxml.jrxml;

import jrxml.jrxml.fileManipulator.FileReader;
import jrxml.jrxml.fileManipulator.FilesGetter;
import jrxml.jrxml.reportsDesign.ReportDesign;
import net.sf.jasperreports.components.ComponentsExtensionsRegistryFactory;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.xml.JRXmlWriter;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.*;

@SpringBootApplication
public class JrxmlApplication {
    private static final String FIELD_NAME = "aField";
    private static final String PARAMETER_DATA = "tableData";
    private JasperDesign jasperDesign;




    private static List<Map<String, Object>> tableData() {
        List<Map<String, Object>> data = new ArrayList<>();
        data.add(Collections.singletonMap(FIELD_NAME, "etaoin"));
        data.add(Collections.singletonMap(FIELD_NAME, "shrdlu"));
        data.add(Collections.singletonMap(FIELD_NAME, "cmfwyp"));
        data.add(Collections.singletonMap(FIELD_NAME, "vbgkqj"));
        return data;
    }

    public static void main(String[] args) throws JRException {

        FilesGetter filesGetter = new FilesGetter();
        List<File> fileList = filesGetter.getFiles();


        List elements = FileReader.readLineByLine(fileList.get(2).getPath());

        for (Object element : elements) {
            System.out.println(element.toString());
        }



        ReportDesign tableReport = new ReportDesign();
        JasperDesign design = tableReport.createReport();
        JRXmlWriter.writeReport(design, "/home/strudla/Toma/file.jrxml", "UTF-8");
        JasperReport report = JasperCompileManager.compileReport(tableReport.jasperDesign);
        Map<String, Object> params = new HashMap<>();
        List<Map<String, Object>> data = tableData();
        params.put(PARAMETER_DATA, data);

        JasperPrint print = JasperFillManager.fillReport(report, params);
        JasperViewer.viewReport(print);

        SpringApplication.run(JrxmlApplication.class, args);
    }



}
