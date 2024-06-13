package pe.tecnostore.tecnostore.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.tecnostore.tecnostore.model.bd.EnlaceMenu;
import pe.tecnostore.tecnostore.model.bd.Usuario;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("Select coalesce(MAX(u.idusuario), 0) + 1 From Usuario u")
    int obtenerId();

    @Query("Select u from Usuario u where u.username=?1")
    public Usuario iniciarSesion(String username);

    @Query("Select e from RolEnlace re join re.enlace e where re.rol.idrol=?1")
    public List<EnlaceMenu> traerEnlaceUsuario(int codRol);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario set nombre=:nombre, username=:username, password=:password, " +
            "rep_pass=:rep_pass, urlfoto=:urlfoto, nombrefoto=:nombrefoto, dni=:dni, " +
            "idrol=:idrol, activo=:activo WHERE idusuario=:idusuario")
    void actualizarUsuario(String nombre, String username, String password, String rep_pass,
                           String urlfoto, String nombrefoto, String dni, Integer idrol,
                           Boolean activo, Integer idusuario);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario set nombre=:nombre, username=:username, password=:password, " +
            "rep_pass=:rep_pass, dni=:dni, idrol=:idrol, activo=:activo " +
            "WHERE idusuario=:idusuario")
    void actualizarUsuarioSinImagen(String nombre, String username, String password, String rep_pass,
                                    String dni, Integer idrol, Boolean activo, Integer idusuario);

    @Query("SELECT u.idusuario FROM Usuario u WHERE u.username=:nombre")
    int obtenerIdUsuarioxNombre(String nombre);
}
