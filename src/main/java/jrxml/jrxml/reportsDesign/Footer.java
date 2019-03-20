package jrxml.jrxml.reportsDesign;

import net.sf.jasperreports.engine.design.JRDesignBand;

public class Footer {
    private int y;


    public Footer(){
        this(50);
    }

    public Footer( int y) {

        this.y = y;
    }

    public JRDesignBand createHeader(){
        JRDesignBand footer = new JRDesignBand();
        footer.setHeight(y);
        return footer;

    }
}
