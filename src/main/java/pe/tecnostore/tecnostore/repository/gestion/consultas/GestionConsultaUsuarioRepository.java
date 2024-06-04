package pe.tecnostore.tecnostore.repository.gestion.consultas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import pe.tecnostore.tecnostore.model.bd.Usuario;
import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.UsuarioConsultaDTO;

import java.util.List;

@Repository
public interface GestionConsultaUsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select new pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.UsuarioConsultaDTO(u.idusuario, u.nombre, u.fecharegistro, r.descripcion, cast(group_concat(em.descripcion) as string), u.urlfoto) from Usuario u " +
            "join Rol r on u.idrol = r.idrol " +
            "join RolEnlace re on r.idrol = re.rol.idrol " +
            "join EnlaceMenu em on em.idenlace = re.enlace.idenlace " +
            "group by u.idusuario,u.nombre,u.fecharegistro,r.descripcion")
    List<UsuarioConsultaDTO> consultaUsuario();

    @Query("select new pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.UsuarioConsultaDTO(u.idusuario, u.nombre, u.fecharegistro, r.descripcion, cast(group_concat(em.descripcion) as string), u.urlfoto) from Usuario u " +
            "join Rol r on u.idrol = r.idrol " +
            "join RolEnlace re on r.idrol = re.rol.idrol " +
            "join EnlaceMenu em on em.idenlace = re.enlace.idenlace " +
            "where u.idrol = :idrol " +
            "group by u.idusuario,u.nombre,u.fecharegistro,r.descripcion")
    List<UsuarioConsultaDTO> consultaUsuarioxRol(int idrol);
}
