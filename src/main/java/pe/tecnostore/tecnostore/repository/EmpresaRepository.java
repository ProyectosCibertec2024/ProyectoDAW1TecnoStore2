package pe.tecnostore.tecnostore.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.tecnostore.tecnostore.model.bd.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    @Query(value = "SELECT coalesce(max(e.idempresa), 0) + 1 FROM Empresa e")
    int obtenerCod();

    @Transactional
    @Modifying
    @Query("update Empresa set nomempresa = :nomempresa, direccion = :direccion, telefono = :telefono, idtipoempresa = :idtipoempresa, email = :email, ruc = :ruc, activo = :activo WHERE idempresa = :idempresa")
    void actualizarEmpresa(@Param("nomempresa") String nomempresa, @Param("direccion") String direccion, @Param("telefono") String telefono, @Param("idtipoempresa") Integer idtipoempresa, @Param("email") String email, @Param("ruc") String ruc, @Param("activo") Boolean activo, @Param("idempresa") Integer idempresa);

    @Query("SELECT COUNT(*) FROM Empresa")
    int obtenerCantidadEmpresa();
}
