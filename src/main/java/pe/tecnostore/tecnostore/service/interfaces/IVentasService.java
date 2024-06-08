package pe.tecnostore.tecnostore.service.interfaces;

import pe.tecnostore.tecnostore.model.bd.Venta;

import java.util.List;

public interface IVentasService {
    List<Venta> listadoVentas();
    void guardarVentas();
}
