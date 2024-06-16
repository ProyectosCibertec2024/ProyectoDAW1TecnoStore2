package pe.tecnostore.tecnostore.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.tecnostore.tecnostore.model.bd.Factura;
import pe.tecnostore.tecnostore.model.dto.object.reportes.FacturaConsultaFechaDTO;
import pe.tecnostore.tecnostore.model.dto.object.reportes.FacturaDTO;
import pe.tecnostore.tecnostore.repository.FacturaRepository;
import pe.tecnostore.tecnostore.service.interfaces.IFacturaService;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class FacturaService implements IFacturaService {
    private FacturaRepository facturaRepository;

    @Override
    public List<Factura> listadoFacturas() {
        return facturaRepository.findAll();
    }

    @Override
    public void guardarFactura(Factura factura) {
        facturaRepository.save(factura);
    }

    @Override
    public Factura buscarFactura(int id) {
        return facturaRepository.findById(id).orElse(null);
    }

    @Override
    public Factura consultarFacturaxIdVenta(int idventa) {
        return facturaRepository.consultarFacturaxIdVenta(idventa);
    }

    @Override
    public int obtenerNumeroFacturaGenerado() {
        return facturaRepository.obtenerNumeroFacturaGenerado();
    }

    @Override
    public List<FacturaDTO> consultarFacturaIdVenta(int idventa) {
        return facturaRepository.consultarFacturaIdVenta(idventa);
    }

    @Override
    public int obtenerIdFactura() {
        return facturaRepository.obtenerIdFactura();
    }

    @Override
    public void actualizarFacturaUrlxNombre(Factura factura) {
        String urlfactura = factura.getUrlfactura();;
        String nomfactura = factura.getNomfactura();
        Integer idfactura = factura.getIdfactura();
        facturaRepository.actualizarFacturaUrlxNombre(urlfactura, nomfactura, idfactura);
    }

    @Override
    public List<FacturaConsultaFechaDTO> consultafechaFactura(LocalDate fecha1, LocalDate fecha2) {
        return facturaRepository.consultafechaFactura(fecha1, fecha2);
    }
}
