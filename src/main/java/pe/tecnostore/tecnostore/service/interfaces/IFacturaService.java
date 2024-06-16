package pe.tecnostore.tecnostore.service.interfaces;

import pe.tecnostore.tecnostore.model.bd.Factura;
import pe.tecnostore.tecnostore.model.dto.object.reportes.FacturaConsultaFechaDTO;
import pe.tecnostore.tecnostore.model.dto.object.reportes.FacturaDTO;

import java.time.LocalDate;
import java.util.List;

public interface IFacturaService {
    List<Factura> listadoFacturas();
    void guardarFactura(Factura factura);
    Factura buscarFactura(int id);
    Factura consultarFacturaxIdVenta(int idventa);
    int obtenerNumeroFacturaGenerado();
    List<FacturaDTO> consultarFacturaIdVenta( int idventa);
    int obtenerIdFactura();
    void actualizarFacturaUrlxNombre(Factura factura);
    List<FacturaConsultaFechaDTO> consultafechaFactura(LocalDate fecha1, LocalDate fecha2);
}
