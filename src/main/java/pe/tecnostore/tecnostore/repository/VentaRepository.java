package pe.tecnostore.tecnostore.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import pe.tecnostore.tecnostore.model.bd.Venta;
import pe.tecnostore.tecnostore.model.dto.object.dashboard.VentaDTO;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    Venta findTopByOrderByIdventaDesc();

    @Query("SELECT coalesce(max(v.idventa), 0) + 1 FROM Venta v")
    int obtenerIdVenta();

    @Transactional
    @Modifying
    @Query(value = "UPDATE venta set idcliente = :idcliente WHERE idventa=:idventa", nativeQuery = true)
    void guardarVenta(@RequestParam("idcliente") int idcliente, @RequestParam("idventa") int idventa);

    @Query("SELECT v FROM Venta v WHERE v.idusuario=:idusuario")
    List<Venta> consultaVentaxUsuario(int idusuario);

    @Transactional
    @Modifying
    @Query("UPDATE Venta SET total=:total WHERE idventa=:idventa")
    int actualizarTotalVenta(double total, int idventa);

    @Query("SELECT v.total FROM Venta v WHERE v.idventa=:idventa")
    double consultarVentaToIdVentaxTotal(@Param("idventa") int idventa);

    @Query("SELECT new pe.tecnostore.tecnostore.model.dto.object.dashboard.VentaDTO(v.fecharegistro, SUM(v.total)) " +
            "FROM Venta v " +
            "WHERE v.fecharegistro BETWEEN :incio AND :fin " +
            "GROUP BY v.fecharegistro " +
            "ORDER BY v.fecharegistro ASC")
    List<VentaDTO> obtnerVentasxMes(@Param("incio") LocalDate incio, @Param("fin") LocalDate fin);
}
