package pe.tecnostore.tecnostore.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.tecnostore.tecnostore.model.bd.EnlaceMenu;
import pe.tecnostore.tecnostore.model.bd.Usuario;
import pe.tecnostore.tecnostore.repository.UsuarioRepository;
import pe.tecnostore.tecnostore.service.interfaces.IUsuarioService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {

    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder encoder;

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public int obtenerId() {
        return usuarioRepository.obtenerId();
    }

    @Override
    public void guardarUsuarioGestion(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario buscarUsuario(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public List<Usuario> listaUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public void actualizarUsuario(String nombre, String username, String password, String rep_pass, String urlfoto, String nombrefoto, String dni, Integer idrol, Boolean activo, Integer idusuario) {
        usuarioRepository.actualizarUsuario(nombre,username,password,rep_pass,urlfoto,nombrefoto,dni,idrol,activo,idusuario);
    }

    @Override
    public void actualizarUsuarioSinImagen(String nombre, String username, String password, String rep_pass, String dni, Integer idrol, Boolean activo, Integer idusuario) {
        usuarioRepository.actualizarUsuarioSinImagen(nombre,username,password,rep_pass,dni,idrol,activo,idusuario);
    }

    @Override
    public Usuario iniciarSesion(String username) {
        return usuarioRepository.iniciarSesion(username);
    }

    @Override
    public List<EnlaceMenu> traerEnlaceUsuario(int codRol) {
        return usuarioRepository.traerEnlaceUsuario(codRol);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Usuario bean = usuarioRepository.iniciarSesion(username);
            if (bean == null) {
                throw new UsernameNotFoundException("Usuario no encontrado");
            }
            if(bean.getActivo() == false) {
                throw new UsernameNotFoundException("El Usuario Esta Deshabilitado");
            }
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority(bean.getRol().getDescripcion()));
            return new User(username, bean.getPassword(), authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Error al cargar el usuario", e);
        }
    }
}
