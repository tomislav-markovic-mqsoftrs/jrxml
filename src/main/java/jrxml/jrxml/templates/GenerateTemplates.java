package jrxml.jrxml.templates;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;

import java.util.Map;

public class GenerateTemplates {


    public static void generateTemplates(Map<String, JasperDesign> templateDesignsWitnNames, String folderPath) {
        templateDesignsWitnNames.forEach((name, jasperDesign) -> {
            try {
                GenerateTemplate.generate(jasperDesign,folderPath + name);
                System.out.println("GENERATED!" + name);

            } catch (JRException e) {
                e.printStackTrace();
            }
        });
    }
}
