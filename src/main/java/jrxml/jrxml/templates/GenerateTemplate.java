package jrxml.jrxml.templates;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlWriter;

public class GenerateTemplate {
    public static void generate(JasperDesign templateDesign, String filePathAndName) throws JRException {
        JRXmlWriter.writeReport(templateDesign, filePathAndName, "UTF-8");
    }

    public static void generate(JasperDesign templateDesign, String filePathAndName, String encoding) throws JRException {
        JRXmlWriter.writeReport(templateDesign, filePathAndName, encoding);
    }

}
