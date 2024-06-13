package pe.tecnostore.tecnostore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.tecnostore.tecnostore.model.bd.DetalleVenta;

import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
    @Query("SELECT COALESCE(MAX(d.iddetalleventa), 0) + 1 FROM DetalleVenta d")
    int obtenerIdDetalleVenta();

    @Query("SELECT d FROM DetalleVenta d WHERE d.idventa =:idventa")
    List<DetalleVenta> obtenerIdVentaToDetalleVenta(int idventa);

    @Query("select sum(dv.subtotal) " +
            "from Venta v " +
            "join v.detalleVentaList dv join v.usuario u join v.cliente c " +
            "where v.idventa =:idventa")
    Double totalVenta(int idventa);
}
