package pe.tecnostore.tecnostore.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.tecnostore.tecnostore.model.bd.Rol;
import pe.tecnostore.tecnostore.model.dto.response.RespuestaResponse;
import pe.tecnostore.tecnostore.service.interfaces.IRolService;

import java.util.List;

@AllArgsConstructor
@Controller
public class RolController {

    private IRolService rolService;

    @GetMapping(value = "/roles-gestion")
    public String roles(Model model) {
        model.addAttribute("rol", rolService.listadoRols());
        return "backoffice/inventario/GestionUsuario/roles/frmroles";
    }

    @GetMapping(value = "/rol-list")
    @ResponseBody
    public List<Rol> listaRoles() {
        return rolService.listadoRols();
    }

    @GetMapping(value = "/idrol")
    @ResponseBody
    public int obtenerIdRol() {
        return rolService.obtenerIdRol();
    }

    @GetMapping(value = "/rol-gestion/{idrol}")
    @ResponseBody
    public Rol buscarRol(@PathVariable Integer idrol) {
        return rolService.buscarRol(idrol);
    }

    @PostMapping(value = "/roles-gestion")
    @ResponseBody
    public RespuestaResponse guardarRoles(@RequestBody Rol rol) {
        boolean rs = true;
        String men = "Guardado Exitosamente";
        try{
            int cod = rolService.obtenerIdRol();
            if(rol.getIdrol() < cod) {
                rolService.guardarRol(rol);
            }else{
                rolService.guardarRol(rol);
            }
        }catch (Exception e) {
            rs = false;
            men = "Ups, Hubo un error : " + e.getCause().getMessage();
        }
        return RespuestaResponse.builder().resultado(rs).mensaje(men).build();
    }
}
