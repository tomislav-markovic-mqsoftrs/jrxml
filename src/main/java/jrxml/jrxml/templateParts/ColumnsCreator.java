package jrxml.jrxml.templateParts;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;

public class ColumnsCreator {
    public JasperDesign jasperDesign;


    public StandardColumn createStandardColumn(int width, int height, String headerText, String detailExpression) {
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

}
