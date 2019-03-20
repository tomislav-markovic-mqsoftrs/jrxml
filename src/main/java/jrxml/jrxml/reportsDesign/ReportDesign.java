package jrxml.jrxml.reportsDesign;

import jrxml.jrxml.filesManipulator.FileReader;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class ReportDesign {
    private static final String FIELD_NAME = "aField";
    private static final String PARAMETER_DATA = "tableData";
    public JasperDesign jasperDesign;


    //the report

    public JasperDesign createReport(File file) throws JRException {
        //the report
        jasperDesign = new JasperDesign();
        jasperDesign.setName("report");
        jasperDesign.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
        jasperDesign.setPageHeight(1000);
        JRDesignBand body = new JRDesignBand();
        body.setHeight(500);
        List<List<String>> elements = FileReader.readLineByLine(file.getPath());
        int x = 0;
        int y = 0;
        for (List<String> e : elements) {
            JRDesignParameter parameter = new JRDesignParameter();
            parameter.setValueClassName(e.get(1));
            parameter.setName(e.get(0));
            jasperDesign.addParameter(parameter);

            JRDesignTextField textField = new JRDesignTextField(jasperDesign);

            textField.setWidth(100);
            textField.setHeight(30);
            textField.setX(x);
            textField.setY(y);
            y = y + 40;
            if (y > 400) {
                y = 0;
                x = x + 110;
            }
            JRDesignExpression expression = new JRDesignExpression();
            expression.addParameterChunk(e.get(0));
            textField.setExpression(expression);
            body.addElement(textField);

        }




        //CREATE HEADER
        Header header = new Header();
        jasperDesign.setPageHeader(header.createHeader());

        //CREATE BODY
        jasperDesign.setColumnHeader(body);




        //CREATE FOOTER
        Footer footer = new Footer();
        jasperDesign.setPageFooter(footer.createHeader());

//        jasperDesign.setPageHeader();
//        jasperDesign.setTitle(body);


//
//        JRDesignParameter parameter = new JRDesignParameter();
//        parameter.setValueClass(List.class);
//        parameter.setName(PARAMETER_DATA);
//        jasperDesign.addParameter(parameter);
//
//
//        //the subdataset
//        String datasetName = "tableDataset";
//        JRDesignDataset subdataset = new JRDesignDataset(false);
//        subdataset.setName(datasetName);
//        //subdataset field
//
//
//        JRDesignField field = new JRDesignField();
//        field.setValueClass(String.class);
//        field.setName(FIELD_NAME);
//        subdataset.addField(field);
//
//
////        ////VAZNOOOOO!!!
////        FilesGetter filesGetter = new FilesGetter("/home/strudla/Toma/workspace/papa/src/main/java/rs/unicreditbank/papa/entity");
////        List<File> fileList = filesGetter.getFiles();
//
//
//        List<List<String>> elements = FileReader.readLineByLine(file.getPath());
//
//
//        for(List<String> e : elements){
//            JRDesignField f = new JRDesignField();
//            f.setValueClassName(e.get(1));
//            f.setName(e.get(0));
//            subdataset.addField(f);
//
//        }
//
//        jasperDesign.addDataset(subdataset);
//
//        //the table element
//        JRDesignComponentElement tableElement = new JRDesignComponentElement(jasperDesign);
//        tableElement.setX(0);
//        tableElement.setY(0);
//        tableElement.setWidth(1000);
//        tableElement.setHeight(500);
//
//        ComponentKey componentKey = new ComponentKey(ComponentsExtensionsRegistryFactory.NAMESPACE, "c",
//                ComponentsExtensionsRegistryFactory.TABLE_COMPONENT_NAME);
//        tableElement.setComponentKey(componentKey);
//
//
//
//
//        ColumnsCreator column = new ColumnsCreator();
//
//
//
//
//        //first column
//        StandardColumn recNoColumn = column.createStandardColumn(100, 20, "Record", "$V{REPORT_COUNT}");
//        //second column
//        StandardColumn fieldColumn = column.createStandardColumn(100, 20, "Field", "$F{" + FIELD_NAME + "}");
//        //third column
//
//
//        List<Column> columns = new ArrayList<Column>();
//        for(List<String> e : elements){
//            StandardColumn col = column.createStandardColumn(100, 20,e.get(0) , "$F{" + e.get(0) + "}");
//            columns.add(col);
//        }
//
//
//
//
//
//
//
//        TableCreator tableCreator = new TableCreator();
//        StandardTable standardTable = tableCreator.createStandardTable(columns);
//
//        tableElement.setComponent(standardTable);
//
//        JRDesignBand title = new JRDesignBand();
//        title.setHeight(50);
//        title.addElement(tableElement);
//
//
//        jasperDesign.setTitle(title);
        return jasperDesign;
    }
}
