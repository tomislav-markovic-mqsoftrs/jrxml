package jrxml.jrxml.templateParts;

import net.sf.jasperreports.components.table.Column;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import java.util.List;

public class TableCreator {




    public StandardTable createStandardTable(List<Column> columns) {
        //        //the table data source
        JRDesignDatasetRun datasetRun = new JRDesignDatasetRun();
        datasetRun.setDatasetName("tableDataset");
        datasetRun.setDataSourceExpression(new JRDesignExpression(
                "new net.sf.jasperreports.engine.data.JRMapCollectionDataSource($P{" + "tableData" + "})"));



        StandardTable standardTable = new StandardTable();
        standardTable.setDatasetRun(datasetRun);

        for(Column c : columns){
            standardTable.addColumn(c);
        }
        return standardTable;
    }
}
