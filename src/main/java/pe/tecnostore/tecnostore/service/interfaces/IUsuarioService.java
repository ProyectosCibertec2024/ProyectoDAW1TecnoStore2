package pe.tecnostore.tecnostore.service.interfaces;

import pe.tecnostore.tecnostore.model.bd.EnlaceMenu;
import pe.tecnostore.tecnostore.model.bd.Usuario;

import java.util.List;

public interface IUsuarioService {
    Usuario guardarUsuario(Usuario usuario);
    Usuario iniciarSesion(String username);
    List<EnlaceMenu> traerEnlaceUsuario(int codRol);
    //GESTION USUARIOS
    int obtenerId();
    void guardarUsuarioGestion(Usuario usuario);
    Usuario buscarUsuario(int id);
    List<Usuario> listaUsuarios();

    void actualizarUsuario(String nombre, String username, String password, String rep_pass,
                           String urlfoto, String nombrefoto, String dni, Integer idrol,
                           Boolean activo, Integer idusuario);

    void actualizarUsuarioSinImagen(String nombre, String username, String password, String rep_pass,
                                    String dni, Integer idrol, Boolean activo, Integer idusuario);
    int obtenerIdUsuarioxNombre(String nombre);
}
