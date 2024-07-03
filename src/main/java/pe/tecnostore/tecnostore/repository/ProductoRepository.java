package pe.tecnostore.tecnostore.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.tecnostore.tecnostore.model.bd.Producto;
import pe.tecnostore.tecnostore.model.dto.object.dashboard.ProductoDTO;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    @Query(value = "SELECT coalesce(max(p.idproducto), 0) + 1 FROM producto p ", nativeQuery = true)
    int obtenerIdProducto();

    @Modifying
    @Transactional
    @Query("UPDATE Producto set marca=:marca, idcategoria=:idcategoria, stock=:stock, " +
            "urlimagen=:urlimagen, nombreimagen=:nombreimagen, precio=:precio, color=:color," +
            "resolucion=:resolucion, procesador=:procesador, descuento=:descuento," +
            "ram=:ram, espaciodisco=:espaciodisco, pantallatamanio=:pantallatamanio," +
            "idproveedor=:idproveedor, activo=:activo WHERE idproducto=:idproducto")
    void actualizarProducto(String marca, Integer idcategoria, Integer stock, String urlimagen,
                            String nombreimagen, Double precio, String color, String resolucion,
                            String procesador, Double descuento, String ram, String espaciodisco,
                            String pantallatamanio, Integer idproveedor, Boolean activo,
                            Integer idproducto);

    @Modifying
    @Transactional
    @Query("UPDATE Producto set marca=:marca, idcategoria=:idcategoria, stock=:stock, " +
            "precio=:precio, color=:color, resolucion=:resolucion, procesador=:procesador," +
            "descuento=:descuento, ram=:ram, espaciodisco=:espaciodisco, pantallatamanio=:pantallatamanio," +
            "idproveedor=:idproveedor, activo=:activo WHERE idproducto=:idproducto")
    void actualizarProductoSinImagen(String marca, Integer idcategoria, Integer stock,
                            Double precio, String color, String resolucion, String procesador,
                            Double descuento, String ram, String espaciodisco,
                            String pantallatamanio, Integer idproveedor, Boolean activo,
                            Integer idproducto);

    @Query("SELECT COUNT(*) FROM Producto")
    int obtenerCantidadProducto();

    @Query("SELECT new pe.tecnostore.tecnostore.model.dto.object.dashboard.ProductoDTO(p.idproducto, p.marca, SUM(dv.cantidad)) FROM Producto p JOIN p.detalleVentaList dv" +
            " GROUP BY p.idproducto ORDER BY SUM(dv.cantidad) DESC" +
            " LIMIT 5")
    List<ProductoDTO> productosmasvendidos();
}
