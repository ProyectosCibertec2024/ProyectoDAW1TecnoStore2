package pe.tecnostore.tecnostore.repository.gestion.consultas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.tecnostore.tecnostore.model.bd.Producto;
import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.ProductoConsultaDTO;

import java.util.List;

@Repository
public interface GestionConsultaProductoRepository extends JpaRepository<Producto, Integer> {
    @Query("SELECT new pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.ProductoConsultaDTO(p.idproducto, p.marca, c.descripcion, p.precio, pr.nomproveedor, p.fecharegistro, p.urlimagen) " +
            " FROM Producto p" +
            " JOIN p.categoria c" +
            " JOIN p.proveedor pr" +
            " WHERE p.marca LIKE :nomproducto" +
            " group by p.idproducto, p.marca, c.descripcion, p.precio, pr.nomproveedor, p.fecharegistro, p.urlimagen")
    List<ProductoConsultaDTO> consultaProductoXMarca(String nomproducto);

    @Query("SELECT new pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.ProductoConsultaDTO(p.idproducto, p.marca, c.descripcion, p.precio, pr.nomproveedor, p.fecharegistro, p.urlimagen) " +
            " FROM Producto p" +
            " JOIN p.categoria c" +
            " JOIN p.proveedor pr" +
            " group by p.idproducto, p.marca, c.descripcion, p.precio, pr.nomproveedor, p.fecharegistro, p.urlimagen")
    List<ProductoConsultaDTO> consultaProducto();
}
