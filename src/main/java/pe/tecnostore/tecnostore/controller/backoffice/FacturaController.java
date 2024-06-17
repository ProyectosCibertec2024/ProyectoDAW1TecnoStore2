package pe.tecnostore.tecnostore.controller.backoffice;

import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.tecnostore.tecnostore.firebase.storage.ImagenService;
import pe.tecnostore.tecnostore.model.bd.Factura;
import pe.tecnostore.tecnostore.model.dto.object.reportes.FacturaConsultaFechaDTO;
import pe.tecnostore.tecnostore.model.dto.object.reportes.FacturaDTO;
import pe.tecnostore.tecnostore.service.interfaces.IFacturaService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@SessionAttributes({"ENLACES","USUARIO"})

@AllArgsConstructor
@Controller
public class FacturaController {
    private IFacturaService facturaService;
    private ImagenService imagenService;

    @GetMapping(value = "/reportes")
    public String reportes(Model model) {
        model.addAttribute("facturalist", facturaService.consultarFactura());
        return "backoffice/reportes/frmreportes";
    }

    @GetMapping(value = "/factura/{numerofactura}")
    @ResponseBody
    public Factura buscarFactura(@PathVariable Integer numerofactura) {
        return facturaService.buscarFacturaxNumeroFactura(numerofactura);
    }

    @PostMapping(value = "/factura")
    public String actualizarFactura(@ModelAttribute Factura factura) {
        String urlfactura = "";
        try {
            Factura buscado = facturaService.buscarFacturaxNumeroFactura(factura.getNumerofactura());
            buscado.setEstado(factura.getEstado());
            facturaService.guardarFactura(buscado);

            List<FacturaDTO> facturars = facturaService.consultarFacturaIdVenta(buscado.getIdventa());
            InputStream jasperStream = getClass().getResourceAsStream("/factura.jasper");
            if (jasperStream == null) {
                throw new RuntimeException("El archivo factura.jasper no se encontro.");
            }

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(facturars);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
            //Map<String, Object> parameters = new HashMap<>();
            //parameters.put("idventa", idventa);
            //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            File archivoPdf = File.createTempFile("tempPdf", ".pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(archivoPdf));

            String nombreArchivo = buscado.getNomfactura();
            String urlpdf = imagenService.uploadFile(archivoPdf, nombreArchivo, "application/pdf", "facturas");

            factura.setUrlfactura(urlpdf);
            factura.setNomfactura(nombreArchivo);
            facturaService.actualizarFacturaUrlxNombre(factura);

            archivoPdf.delete();

            urlfactura = "redirect:" + factura.getUrlfactura();
        }catch (Exception e) {
            System.out.println("Error En Modificar : " + e.getMessage());
        }
        return urlfactura;
    }

    /*@GetMapping(value = "/reportesconsulta-list")
    @ResponseBody
    public List<FacturaConsultaFechaDTO> reporteFacturaConsultaFechaDTOS() {
        return facturaService.consultarFactura();
    }*/

    @PostMapping(value = "/reportes")
    public String reportesConsulta(@RequestParam("fecha1") LocalDate fecha1,
                                   @RequestParam("fecha2") LocalDate fecha2,
                                   Model model) {
        try{
            if(fecha1 == null && fecha2 == null) {
                model.addAttribute("error", "Ingrese Las Fechas");
            }else {
                List<FacturaConsultaFechaDTO> lista = facturaService.consultafechaFactura(fecha1, fecha2);
                if(lista.isEmpty()) {
                    model.addAttribute("error", "No Se Encontraron Facturas");
                }else {
                    model.addAttribute("listafechas", lista);
                }
            }
        }catch (Exception e) {
            System.out.println("Error en listar por fecha : " + e.getMessage());
        }
        return "backoffice/reportes/frmreportes";
    }

    @GetMapping("/generar-reporte/{idventa}")
    public String mostrarFormularioGenerarReporte(@PathVariable Integer idventa, Model model) {
        model.addAttribute("idventa", idventa);
        model.addAttribute("idfacturaautogenerado", facturaService.obtenerIdFactura());
        return "backoffice/reportes/frmgenerarventa";
    }

    @PostMapping(value = "/generar-reporte/{idventa}")
    public String generarReporte(@PathVariable Integer idventa,
                                 @RequestParam("idfactura") int idfactura) {
        String urlfactura;
        try {
            Factura facturaexistente = facturaService.consultarFacturaxIdVenta(idventa);
            if(facturaexistente != null && facturaexistente.getIdfactura() > 0) {
                urlfactura = "redirect:" + facturaexistente.getUrlfactura();
            }else {
                int numfactura = facturaService.obtenerNumeroFacturaGenerado();
                Factura factura = new Factura();
                factura.setIdfactura(idfactura);
                factura.setIdventa(idventa);
                LocalDate date = LocalDate.now();
                factura.setNumerofactura(numfactura);
                factura.setFechaemision(date);
                factura.setEstado(true);
                facturaService.guardarFactura(factura);

                List<FacturaDTO> facturars = facturaService.consultarFacturaIdVenta(idventa);
                InputStream jasperStream = getClass().getResourceAsStream("/factura.jasper");
                if (jasperStream == null) {
                    throw new RuntimeException("El archivo factura.jasper no se encontro.");
                }

                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(facturars);
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
                //Map<String, Object> parameters = new HashMap<>();
                //parameters.put("idventa", idventa);
                //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

                File archivoPdf = File.createTempFile("tempPdf", ".pdf");
                JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(archivoPdf));

                String nombreArchivo = "factura_" + numfactura + ".pdf";
                String urlpdf = imagenService.uploadFile(archivoPdf, nombreArchivo, "application/pdf", "facturas");

                factura.setUrlfactura(urlpdf);
                factura.setNomfactura(nombreArchivo);
                facturaService.actualizarFacturaUrlxNombre(factura);

                archivoPdf.delete();

                urlfactura = "redirect:" + factura.getUrlfactura();
            }
        }catch (Exception e) {
            System.out.println("Error En Generar Reporte : " + e.getMessage());
            urlfactura = "Error en : " + e.getMessage();
        }
        return urlfactura;
    }
}
