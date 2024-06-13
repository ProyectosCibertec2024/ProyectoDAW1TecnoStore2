package pe.tecnostore.tecnostore.service.interfaces;

import pe.tecnostore.tecnostore.model.bd.Venta;

import java.util.List;

public interface IVentasService {
    List<Venta> listadoVentas();
    void guardarVentas(Venta venta);
    Venta buscarVenta(int id);
    String generarNumeroVenta();
    int obtenerIdVenta();
    void actualizarventa(int idcliente, int idventa);
    List<Venta> consultaVentaxUsuario(int idusuario);
}
