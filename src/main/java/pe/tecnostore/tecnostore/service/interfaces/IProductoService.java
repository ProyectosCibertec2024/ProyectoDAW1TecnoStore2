package pe.tecnostore.tecnostore.service.interfaces;

import pe.tecnostore.tecnostore.model.bd.Producto;

import java.util.List;

public interface IProductoService {
    List<Producto> listaProductos();
    void guardarProducto(Producto producto);
    Producto buscarProducto(int id);
    int obtenerIdProducto();
    void actualizarProducto(String marca, Integer idcategoria, Integer stock, String urlimagen,
                            String nombreimagen, Double precio, String color, String resolucion,
                            String procesador, Double descuento, String ram, String espaciodisco,
                            String pantallatamanio, Integer idproveedor, Boolean activo, Integer idproducto);
    void actualizarProductoSinImagen(String marca, Integer idcategoria, Integer stock,
                                     Double precio, String color, String resolucion, String procesador,
                                     Double descuento, String ram, String espaciodisco,
                                     String pantallatamanio, Integer idproveedor, Boolean activo,
                                     Integer idproducto);
}
