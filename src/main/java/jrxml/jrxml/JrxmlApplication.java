package jrxml.jrxml;

import jrxml.jrxml.fileManipulator.FileReader;
import jrxml.jrxml.fileManipulator.FilesGetter;
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


        JrxmlApplication tableReport = new JrxmlApplication();
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

    private StandardColumn createColumn(int width, int height, String headerText, String detailExpression) {
        StandardColumn column = new StandardColumn();
        column.setWidth(width);

        //column header
        DesignCell header = new DesignCell();
        header.setDefaultStyleProvider(jasperDesign);
        header.getLineBox().getPen().setLineWidth(1f);
        header.setHeight(height);

        JRDesignStaticText headerElement = new JRDesignStaticText(jasperDesign);
        headerElement.setX(0);
        headerElement.setY(0);
        headerElement.setWidth(width);
        headerElement.setHeight(height);
        headerElement.setText(headerText);

        header.addElement(headerElement);
        column.setColumnHeader(header);

        //column detail
        DesignCell detail = new DesignCell();
        detail.setDefaultStyleProvider(jasperDesign);
        detail.getLineBox().getPen().setLineWidth(1f);
        detail.setHeight(height);

        JRDesignTextField detailElement = new JRDesignTextField(jasperDesign);
        detailElement.setX(0);
        detailElement.setY(0);
        detailElement.setWidth(width);
        detailElement.setHeight(height);
        detailElement.setExpression(new JRDesignExpression(detailExpression));

        detail.addElement(detailElement);
        column.setDetailCell(detail);

        return column;
    }

    public JasperDesign createReport() throws JRException {
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
        FilesGetter filesGetter = new FilesGetter();
        List<File> fileList = filesGetter.getFiles();


        List<List<String>> elements = FileReader.readLineByLine(fileList.get(2).getPath());
        JRDesignField f = new JRDesignField();

        f.setValueClassName(elements.get(0).get(1));
        f.setName(elements.get(0).get(0));
        subdataset.addField(f);

//		for(  List element : elements){
//			JRDesignField f = new JRDesignField();
//			f.setValueClassName(element.get(1).toString());
//			f.setName(element.get(0).toString());
//			subdataset.addField(f);
//		}


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

        StandardTable table = new StandardTable();

        //the table data source
        JRDesignDatasetRun datasetRun = new JRDesignDatasetRun();
        datasetRun.setDatasetName(datasetName);
        datasetRun.setDataSourceExpression(new JRDesignExpression(
                "new net.sf.jasperreports.engine.data.JRMapCollectionDataSource($P{" + PARAMETER_DATA + "})"));
        table.setDatasetRun(datasetRun);

        //first column
        StandardColumn recNoColumn = createColumn(100, 20, "Record", "$V{REPORT_COUNT}");
        table.addColumn(recNoColumn);

        //second column
        StandardColumn fieldColumn = createColumn(100, 20, "Field", "$F{" + FIELD_NAME + "}");
        table.addColumn(fieldColumn);

        //third column
        //second column
        StandardColumn idColumn = createColumn(100, 20,elements.get(0).get(0) , "$F{" + elements.get(0).get(0) + "}");
        table.addColumn(idColumn);


        tableElement.setComponent(table);

        JRDesignBand title = new JRDesignBand();
        title.setHeight(50);
        title.addElement(tableElement);


        jasperDesign.setTitle(title);
        return jasperDesign;
    }

}
