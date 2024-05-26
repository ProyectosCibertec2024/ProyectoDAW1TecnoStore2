package pe.tecnostore.tecnostore.service.interfaces;

import pe.tecnostore.tecnostore.model.bd.Proveedor;

import java.util.List;

public interface IProveedorService {
    List<Proveedor> listaProveedores();
    void guardarProveedor(Proveedor proveedor);
    Proveedor buscarProveedor(int id);
    int obtenerIdProveedor();
    void actualizarProveedor(String nomproveedor, String telefono, String correo,
                             Boolean estado, Integer idempresa, Integer idproveedor);
}
