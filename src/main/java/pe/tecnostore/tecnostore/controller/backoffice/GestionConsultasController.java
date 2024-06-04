package pe.tecnostore.tecnostore.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.EmpresaConsultaDTO;
import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.UsuarioConsultaDTO;
import pe.tecnostore.tecnostore.service.interfaces.IRolService;
import pe.tecnostore.tecnostore.service.interfaces.ITipoEmpresaService;
import pe.tecnostore.tecnostore.service.interfaces.gestion.consultas.IGestionConsultaEmpresaService;
import pe.tecnostore.tecnostore.service.interfaces.gestion.consultas.IGestionConsultaUsuarioService;

import java.util.List;

@SessionAttributes({"ENLACES","USUARIO"})

@AllArgsConstructor
@Controller
public class GestionConsultasController {

    private IGestionConsultaUsuarioService gestionConsultaUsuarioService;
    private IRolService rolService;
    private ITipoEmpresaService tipoEmpresaService;
    private IGestionConsultaEmpresaService gestionConsultaEmpresaService;

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
                List<UsuarioConsultaDTO> lista = gestionConsultaUsuarioService.consultaUsuarioxRol(idrol);
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
    @GetMapping(value = "/gestionconsulta-empresas")
    public String gestionConsultaEmpresa(Model model) {
        model.addAttribute("tipoempresagestion", tipoEmpresaService.listadoTipoEmpresas());
        return "backoffice/inventario/GestionConsultas/empresa/frmgestionconsultaempresa";
    }

    @PostMapping(value = "/gestionconsulta-empresas")
    public String gestionConsultaEmpresa(@RequestParam("idtipoempresa") int idtipoempresa,
                                         Model model) {
        try{
            String mensaje;
            if(idtipoempresa == 0) {
                mensaje = "Seleccione El Tipo de Empresa";
                model.addAttribute("error", mensaje);
            }else {
                List<EmpresaConsultaDTO> lista = gestionConsultaEmpresaService.consultaEmpresa(idtipoempresa);
                if(lista.isEmpty()) {
                    mensaje = "No se encontraron empresas para el tipo empresa seleccionado";
                    model.addAttribute("error", mensaje);
                }else {
                    model.addAttribute("empresaconsultagestion", lista);
                }
            }
            model.addAttribute("tipoempresagestion", tipoEmpresaService.listadoTipoEmpresas());
        }catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return "backoffice/inventario/GestionConsultas/empresa/frmgestionconsultaempresa";
    }

    /*GESTION CONSULTAS PROVEEDOR*/


    /*GESTION CONSULTAS PRODUCTO*/


}
