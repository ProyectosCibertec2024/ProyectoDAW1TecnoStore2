package pe.tecnostore.tecnostore.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.tecnostore.tecnostore.model.bd.Producto;
import pe.tecnostore.tecnostore.repository.ProductoRepository;
import pe.tecnostore.tecnostore.service.interfaces.IProductoService;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductoService implements IProductoService {

    private ProductoRepository productoRepository;

    @Override
    public List<Producto> listaProductos() {
        return productoRepository.findAll();
    }

    @Override
    public void guardarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public Producto buscarProducto(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public int obtenerIdProducto() {
        return productoRepository.obtenerIdProducto();
    }

    @Override
    public void actualizarProducto(String marca, Integer idcategoria, Integer stock, String urlimagen, String nombreimagen, Double precio, String color, String resolucion, String procesador, Double descuento, String ram, String espaciodisco, String pantallatamanio, Integer idproveedor, Boolean activo, Integer idproducto) {
        productoRepository.actualizarProducto(marca, idcategoria, stock, urlimagen, nombreimagen, precio, color, resolucion, procesador, descuento, ram, espaciodisco, pantallatamanio, idproveedor, activo, idproducto);
    }

    @Override
    public void actualizarProductoSinImagen(String marca, Integer idcategoria, Integer stock, Double precio, String color, String resolucion, String procesador, Double descuento, String ram, String espaciodisco, String pantallatamanio, Integer idproveedor, Boolean activo, Integer idproducto) {
        productoRepository.actualizarProductoSinImagen(marca,idcategoria,stock,precio,color,resolucion,procesador,descuento,ram,espaciodisco,pantallatamanio,idproveedor,activo,idproducto);
    }
}
