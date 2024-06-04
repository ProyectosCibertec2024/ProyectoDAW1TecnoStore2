package pe.tecnostore.tecnostore.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.UsuarioConsultaDTO;
import pe.tecnostore.tecnostore.repository.gestion.consultas.GestionConsultaUsuarioRepository;
import pe.tecnostore.tecnostore.service.interfaces.IRolService;
import pe.tecnostore.tecnostore.service.interfaces.gestion.consultas.IGestionConsultaUsuarioService;

import java.util.List;

@SessionAttributes({"ENLACES","USUARIO"})

@AllArgsConstructor
@Controller
public class GestionConsultasController {

    private final GestionConsultaUsuarioRepository gestionConsultaUsuarioRepository;
    private IGestionConsultaUsuarioService gestionConsultaUsuarioService;
    private IRolService rolService;

    /**GESTION CONSULTAS**/
    @GetMapping(value = "/gestion-consultas")
    public String gestionConsultas() {
        return "backoffice/inventario/GestionConsultas/frmgestionconsulta";
    }

    /*GESTION CONSULTAS USUARIOS*/
    @GetMapping(value = "/gestionconsulta-usuarios")
    public String gestionConsultasUsuarios(Model model) {
        model.addAttribute("rolesgestionuser", rolService.listadoRols());
        return "backoffice/inventario/GestionConsultas/usuarios/frmconsultausuario";
    }

    @PostMapping(value = "/gestionconsulta-usuarios")
    public String gestionConsultaUsuarios(@RequestParam("idrol") int idrol, Model model) {
        try {
            String mensaje;
            if(idrol == 0) {
                mensaje = "Seleccione Un Rol";
                model.addAttribute("error", mensaje);
                model.addAttribute("rolesgestionuser", rolService.listadoRols());
            }else {
                List<UsuarioConsultaDTO> lista = gestionConsultaUsuarioRepository.consultaUsuarioxRol(idrol);
                if(lista.isEmpty()) {
                    model.addAttribute("error", "No se encontraron usuarios para el rol seleccionado");
                    model.addAttribute("rolesgestionuser", rolService.listadoRols());
                } else {
                    model.addAttribute("gestuserconsulta", lista);
                    model.addAttribute("rolesgestionuser", rolService.listadoRols());
                }
            }
        }catch (Exception e) {
            System.out.println("Error en : " + e.getMessage());
        }
        return "backoffice/inventario/GestionConsultas/usuarios/frmconsultausuario";
    }
    /*GESTION CONSULTAS EMPRESA*/


    /*GESTION CONSULTAS PROVEEDOR*/


    /*GESTION CONSULTAS PRODUCTO*/


}
