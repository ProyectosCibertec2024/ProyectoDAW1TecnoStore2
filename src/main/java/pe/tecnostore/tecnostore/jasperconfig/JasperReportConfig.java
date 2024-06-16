package pe.tecnostore.tecnostore.jasperconfig;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRSaver;
import java.io.InputStream;


public class JasperReportConfig {

    public JasperReport compileReport() throws JRException {
        InputStream employeeReportStream = getClass().getResourceAsStream("/factura.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(employeeReportStream);
        JRSaver.saveObject(jasperReport, "src/main/resources/factura.jasper");
        return jasperReport;
    }
}
