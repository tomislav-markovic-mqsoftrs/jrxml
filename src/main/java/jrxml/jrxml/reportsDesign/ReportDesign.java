package jrxml.jrxml.reportsDesign;

import jrxml.jrxml.filesManipulator.FileReader;
import jrxml.jrxml.filesManipulator.FilesGetter;
import jrxml.jrxml.templateParts.ColumnsCreator;
import jrxml.jrxml.templateParts.TableCreator;
import net.sf.jasperreports.components.ComponentsExtensionsRegistryFactory;
import net.sf.jasperreports.components.table.Column;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.xml.JRXmlWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ReportDesign {
    private static final String FIELD_NAME = "aField";
    private static final String PARAMETER_DATA = "tableData";
    public JasperDesign jasperDesign;


public void createReports(List<File> files) throws JRException {
 for(File file: files){
     System.out.println(file.getName());
     JasperDesign template = createReport(file);
     String fileName = file.getName().split("\\.")[0] + "Template.jrxml";
     JRXmlWriter.writeReport(template, "/home/toma/Toma/"+fileName, "UTF-8");
 }
}
    //the report

    public JasperDesign createReport(File file) throws JRException {
        //the report
        jasperDesign = new JasperDesign();
        jasperDesign.setName("TableReport");
        jasperDesign.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

        JRDesignParameter parameter = new JRDesignParameter();
        parameter.setValueClass(List.class);
        parameter.setName(PARAMETER_DATA);
        jasperDesign.addParameter(parameter);


        //the subdataset
        String datasetName = "tableDataset";
        JRDesignDataset subdataset = new JRDesignDataset(false);
        subdataset.setName(datasetName);
        //subdataset field


        JRDesignField field = new JRDesignField();
        field.setValueClass(String.class);
        field.setName(FIELD_NAME);
        subdataset.addField(field);


        ////VAZNOOOOO!!!
        FilesGetter filesGetter = new FilesGetter("/home/toma/Toma/workspace/papa/src/main/java/rs/unicreditbank/papa/entity");
        List<File> fileList = filesGetter.getFiles();


        List<List<String>> elements = FileReader.readLineByLine(file.getPath());


        for(List<String> e : elements){
            JRDesignField f = new JRDesignField();
            f.setValueClassName(e.get(1));
            f.setName(e.get(0));
            subdataset.addField(f);

        }

        jasperDesign.addDataset(subdataset);

        //the table element
        JRDesignComponentElement tableElement = new JRDesignComponentElement(jasperDesign);
        tableElement.setX(0);
        tableElement.setY(0);
        tableElement.setWidth(200);
        tableElement.setHeight(50);

        ComponentKey componentKey = new ComponentKey(ComponentsExtensionsRegistryFactory.NAMESPACE, "c",
                ComponentsExtensionsRegistryFactory.TABLE_COMPONENT_NAME);
        tableElement.setComponentKey(componentKey);




        ColumnsCreator column = new ColumnsCreator();




        //first column
        StandardColumn recNoColumn = column.createStandardColumn(100, 20, "Record", "$V{REPORT_COUNT}");
        //second column
        StandardColumn fieldColumn = column.createStandardColumn(100, 20, "Field", "$F{" + FIELD_NAME + "}");
        //third column


        List<Column> columns = new ArrayList<Column>();
        for(List<String> e : elements){
            StandardColumn col = column.createStandardColumn(100, 20,e.get(0) , "$F{" + e.get(0) + "}");
            columns.add(col);
        }







        TableCreator tableCreator = new TableCreator();
        StandardTable standardTable = tableCreator.createStandardTable(columns);

        tableElement.setComponent(standardTable);

        JRDesignBand title = new JRDesignBand();
        title.setHeight(50);
        title.addElement(tableElement);


        jasperDesign.setTitle(title);
        return jasperDesign;
    }
}
