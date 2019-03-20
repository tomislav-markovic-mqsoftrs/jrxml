package jrxml.jrxml.reportsDesign;

import net.sf.jasperreports.engine.design.JRDesignBand;

public class Header {
    private int y;


    public Header(){
        this(100);
    }

    public Header( int y) {

        this.y = y;
    }

    public JRDesignBand createHeader(){
        JRDesignBand header = new JRDesignBand();
        header.setHeight(y);
        return header;

    }
}
