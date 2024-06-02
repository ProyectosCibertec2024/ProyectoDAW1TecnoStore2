package pe.tecnostore.tecnostore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.tecnostore.tecnostore.model.bd.RolEnlace;
import pe.tecnostore.tecnostore.model.dto.object.RolMenuDTO;
import pe.tecnostore.tecnostore.model.dto.object.RolMenuIdRolDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolEnlaceRepository extends JpaRepository<RolEnlace, Integer> {
    Optional<RolEnlace> findByPkIdrolAndPkIdenlace(Integer idrol, Integer idenlace);

    @Query(value = "SELECT new pe.tecnostore.tecnostore.model.dto.object.RolMenuDTO(r.idrol, r.descripcion, CAST(GROUP_CONCAT(em.descripcion) as string), re.enlace.idenlace) " +
            "FROM Rol r JOIN RolEnlace re ON r.idrol = re.rol.idrol JOIN EnlaceMenu em ON re.enlace.idenlace = em.idenlace GROUP BY r.idrol")
    List<RolMenuDTO> obtenerMenusRol();

    @Query(value = "SELECT new pe.tecnostore.tecnostore.model.dto.object.RolMenuIdRolDTO(r.idrol, r.descripcion, CAST(GROUP_CONCAT(em.descripcion) as string), CAST(group_concat(em.idenlace) as string))" +
            "FROM Rol r JOIN RolEnlace re ON r.idrol = re.rol.idrol JOIN EnlaceMenu em ON re.enlace.idenlace = em.idenlace WHERE re.rol.idrol = :idrol GROUP BY r.idrol")
    RolMenuIdRolDTO obtenerMenusRolxIdRol(int idrol);

    List<RolEnlace> findByPkIdrol(Integer idrol);
}
