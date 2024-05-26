package pe.tecnostore.tecnostore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.tecnostore.tecnostore.model.bd.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    @Query("SELECT coalesce(max(r.idrol), 0) + 1 FROM Rol r")
    int obtenerIdRol();
}
