package pe.tecnostore.tecnostore.service.interfaces;

import pe.tecnostore.tecnostore.model.bd.DetalleVenta;

import java.util.List;

public interface IDetalleVentaService {
    int obtenerIdDetalleVenta();
    List<DetalleVenta> obtenerIdVentaToDetalleVenta(int idventa);
    void guardarDetalleVenta(DetalleVenta detalleVenta);
    DetalleVenta buscarDetalleVenta(int id);
    void actualizarVentaTotal(Integer idventa);
}
