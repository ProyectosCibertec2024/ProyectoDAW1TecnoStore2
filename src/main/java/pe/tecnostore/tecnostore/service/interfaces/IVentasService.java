package pe.tecnostore.tecnostore.service.interfaces;

import pe.tecnostore.tecnostore.model.bd.Venta;
import pe.tecnostore.tecnostore.model.dto.object.dashboard.VentaDTO;

import java.time.LocalDate;
import java.util.List;

public interface IVentasService {
    List<Venta> listadoVentas();
    void guardarVentas(Venta venta);
    Venta buscarVenta(int id);
    String generarNumeroVenta();
    int obtenerIdVenta();
    void actualizarventa(int idcliente, int idventa);
    List<Venta> consultaVentaxUsuario(int idusuario);
    double consultarVentaToIdVentaxTotal(int idventa);
    List<VentaDTO> obtnerVentasxMes(LocalDate incio, LocalDate fin);
}
