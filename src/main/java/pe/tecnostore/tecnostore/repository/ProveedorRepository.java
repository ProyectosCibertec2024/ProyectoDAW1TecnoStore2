package pe.tecnostore.tecnostore.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.tecnostore.tecnostore.model.bd.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    @Query(value = "SELECT coalesce(max(p.idproveedor), 0) + 1 FROM proveedor p", nativeQuery = true)
    int obtenerIdProveedor();

    @Modifying
    @Transactional
    @Query("UPDATE Proveedor set nomproveedor=:nomproveedor, telefono=:telefono, correo=:correo," +
            "estado=:estado, idempresa=:idempresa WHERE idproveedor=:idproveedor")
    void actualizarProveedor(String nomproveedor, String telefono, String correo,
                             Boolean estado, Integer idempresa, Integer idproveedor);

    @Query("SELECT COUNT(*) FROM Proveedor")
    int obtenerCantidadProveedor();
}
