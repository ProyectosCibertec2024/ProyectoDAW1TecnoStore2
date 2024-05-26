package pe.tecnostore.tecnostore.controller.backoffice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.tecnostore.tecnostore.firebase.storage.ImagenService;
import pe.tecnostore.tecnostore.model.bd.EnlaceMenu;
import pe.tecnostore.tecnostore.model.bd.Usuario;
import pe.tecnostore.tecnostore.service.interfaces.IRolService;
import pe.tecnostore.tecnostore.service.interfaces.IUsuarioService;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@SessionAttributes({"ENLACES","USUARIO"})

@AllArgsConstructor
@Controller
public class UsuarioController {

    private IUsuarioService usuarioService;
    private ImagenService imagenService;
    private IRolService rolService;

    /**Principal**/
    @GetMapping("/dashboard")
    public String menu(Authentication auth, Model model) {
        if (auth != null) {
            String username = auth.getName();
            Usuario u = usuarioService.iniciarSesion(username);
            List<EnlaceMenu> lista = usuarioService.traerEnlaceUsuario(u.getRol().getIdrol());
            model.addAttribute("ENLACES", lista);
            model.addAttribute("USUARIO", u);
            return "backoffice/principal/principal";
        }
        return "backoffice/principal/principal";
    }

    /**Logueo**/
    @GetMapping("/login")
    public String logueo() {
        return "backoffice/logueo/login";
    }

    /**Cerrar Sesion y borrar las coookies de auth**/
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    /*GESTION DE USUARIO*/
    @GetMapping(value = "/gestionusuario")
    public String gestionUsuarios(Model model) {
        return "backoffice/inventario/GestionUsuario/gestionUsuario";
    }

    //USUARIO
    @GetMapping(value = "/usuario-gestion")
    public String usuarios(Model model) {
        model.addAttribute("u", new Usuario());
        model.addAttribute("roles", rolService.listadoRols());
        model.addAttribute("listausuarios", usuarioService.listaUsuarios());
        return "backoffice/inventario/GestionUsuario/usuario/frmusuario";
    }

    @GetMapping(value = "/usuario-list")
    @ResponseBody
    public List<Usuario> listaUsuarios() {
        return usuarioService.listaUsuarios();
    }

    @PostMapping(value = "/usuario-gestion")
    @ResponseBody
    public String guardarUsuario(@ModelAttribute("u") Usuario usuario,
                                 @RequestParam("imagen") MultipartFile imagen, Model model) {
        try {
            int cod = usuarioService.obtenerId();
            if(usuario.getIdusuario() < cod) {
                if(!imagen.isEmpty()) {
                    String nombreArchivo = imagen.getOriginalFilename();
                    File archivo = imagenService.convertToFile(imagen, nombreArchivo);
                    String contenido = imagen.getContentType();
                    String imagenUrl = imagenService.uploadFile(archivo, nombreArchivo, contenido, "usuarios");
                    usuario.setNombrefoto(nombreArchivo);
                    usuario.setUrlfoto(imagenUrl);
                    usuarioService.actualizarUsuario(usuario.getNombre(), usuario.getUsername(),
                            usuario.getPassword(),usuario.getRep_pass(),usuario.getUrlfoto(),
                            usuario.getNombrefoto(),usuario.getDni(),usuario.getIdrol(),
                            usuario.getActivo(),usuario.getIdusuario());
                    model.addAttribute("u", new Usuario());
                }else {
                    usuarioService.actualizarUsuarioSinImagen(usuario.getNombre(),
                            usuario.getUsername(),usuario.getPassword(),usuario.getRep_pass(), usuario.getDni(),
                            usuario.getIdrol(), usuario.getActivo(),usuario.getIdusuario());
                    model.addAttribute("u", new Usuario());
                }
            }else {
                if(!imagen.isEmpty()) {
                    String nombreArchivo = imagen.getOriginalFilename();
                    File archivo = imagenService.convertToFile(imagen, nombreArchivo);
                    String contenido = imagen.getContentType();
                    String imagenUrl = imagenService.uploadFile(archivo, nombreArchivo, contenido, "usuarios");
                    usuario.setNombrefoto(nombreArchivo);
                    usuario.setUrlfoto(imagenUrl);
                    LocalDate fecha = LocalDate.now();
                    usuario.setFecharegistro(fecha);
                    usuarioService.guardarUsuario(usuario);
                    model.addAttribute("u", new Usuario());
                }
            }
        }catch (Exception e) {
            System.out.println("Error : " + e.getCause().getMessage());
        }
        return "redirect:/usuario-gestion";
    }

    @GetMapping(value = "/usuario-gestion/{idusuario}")
    @ResponseBody
    public Usuario buscarUsuario(@PathVariable Integer idusuario) {
        return usuarioService.buscarUsuario(idusuario);
    }

    @GetMapping(value = "/idusuario")
    @ResponseBody
    public Integer obtenerIdUsuario() {
        return usuarioService.obtenerId();
    }
}
