package pe.tecnostore.tecnostore.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.tecnostore.tecnostore.model.bd.Factura;
import pe.tecnostore.tecnostore.model.dto.object.reportes.FacturaConsultaFechaDTO;
import pe.tecnostore.tecnostore.model.dto.object.reportes.FacturaDTO;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
    @Query("SELECT f FROM Factura f WHERE f.ventafactura.idventa=:idventa")
    Factura consultarFacturaxIdVenta(@Param("idventa") int idventa);

    @Query("SELECT COALESCE(max(f.numerofactura), 0) + 1 FROM Factura f")
    int obtenerNumeroFacturaGenerado();

    @Query("SELECT new pe.tecnostore.tecnostore.model.dto.object.reportes.FacturaDTO(f.numerofactura, FUNCTION('DATE', f.fechaemision), pr.nomproveedor, e.nomempresa, pr.telefono, \n" +
            "pr.correo, c.dnicliente, c.nomcliente, c.apecliente, c.direccion, c.fonocliente, dv.subtotal,\n" +
            "dv.cantidad, p.marca, ct.descripcion, p.precio, p.descuento, v.total) " +
            "FROM Factura f " +
            " JOIN f.ventafactura v" +
            " JOIN v.cliente c" +
            " JOIN v.detalleVentaList dv" +
            " JOIN dv.producto p" +
            " JOIN p.categoria ct" +
            " JOIN p.proveedor pr" +
            " JOIN pr.empresa e" +
            " WHERE v.idventa=:idventa" +
            " GROUP BY f.numerofactura, f.fechaemision, pr.nomproveedor, e.nomempresa, pr.telefono, \n" +
            "pr.correo, c.dnicliente, c.nomcliente, c.apecliente, c.direccion, c.fonocliente, dv.subtotal, dv.cantidad,\n" +
            "p.marca, p.marca, ct.descripcion, p.precio, p.descuento, v.total")
    List<FacturaDTO> consultarFacturaIdVenta(@Param("idventa") int idventa);

    @Query("SELECT COALESCE(MAX(c.idfactura), 0) + 1 FROM Factura c")
    int obtenerIdFactura();

    @Transactional
    @Modifying
    @Query("UPDATE Factura set urlfactura=:urlfactura, nomfactura=:nomfactura WHERE idfactura=:idfactura")
    void actualizarFacturaUrlxNombre(@Param("urlfactura") String urlfactura,
                                     @Param("nomfactura") String nomfactura,
                                     @Param("idfactura") int idfactura);

    @Query("SELECT new pe.tecnostore.tecnostore.model.dto.object.reportes.FacturaConsultaFechaDTO(f.numerofactura, v.numeroventa, c.nomcliente, c.apecliente, c.dnicliente, c.direccion, c.fonocliente, f.fechaemision, f.urlfactura)" +
            "FROM Factura f" +
            " JOIN f.ventafactura v" +
            " JOIN v.cliente c" +
            " WHERE DATE(f.fechaemision) BETWEEN :fecha1 and :fecha2" +
            " GROUP BY f.numerofactura, v.numeroventa, c.nomcliente, c.apecliente, c.dnicliente, c.direccion, c.fonocliente, f.fechaemision")
    List<FacturaConsultaFechaDTO> consultafechaFactura(@Param("fecha1") LocalDate fecha1, @Param("fecha2") LocalDate fecha2);

    @Query("SELECT new pe.tecnostore.tecnostore.model.dto.object.reportes.FacturaConsultaFechaDTO(f.numerofactura, v.numeroventa, c.nomcliente, c.apecliente, c.dnicliente, c.direccion, c.fonocliente, f.fechaemision, f.urlfactura)" +
            "FROM Factura f" +
            " JOIN f.ventafactura v" +
            " JOIN v.cliente c" +
            " GROUP BY f.numerofactura, v.numeroventa, c.nomcliente, c.apecliente, c.dnicliente, c.direccion, c.fonocliente, f.fechaemision")
    List<FacturaConsultaFechaDTO> consultarFactura();

    @Query("SELECT f FROM Factura f WHERE f.numerofactura=:numerofactura")
    Factura buscarFactura(@Param("numerofactura") Integer numerofactura);
}
