package pe.tecnostore.tecnostore.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.tecnostore.tecnostore.model.bd.RolEnlace;
import pe.tecnostore.tecnostore.model.dto.object.gestion.usuarios.RolMenuDTO;
import pe.tecnostore.tecnostore.model.dto.object.gestion.usuarios.RolMenuIdRolDTO;
import pe.tecnostore.tecnostore.service.interfaces.IEnlaceMenuService;
import pe.tecnostore.tecnostore.service.interfaces.IRolEnlaceService;
import pe.tecnostore.tecnostore.service.interfaces.IRolService;

import java.util.List;

@SessionAttributes({"ENLACES","USUARIO"})

@AllArgsConstructor
@Controller
public class RolEnlaceController {

    private IRolEnlaceService rolEnlaceService;
    private IRolService rolService;
    private IEnlaceMenuService menuService;

    @GetMapping(value = "/rolenlace-gestion")
    public String rolEnlaceGestion(Model model) {
        model.addAttribute("rol", rolService.listadoRols());
        model.addAttribute("menu", menuService.listadoEnlaceMenus());
        model.addAttribute("re", new RolEnlace());
        model.addAttribute("rolenlace", rolEnlaceService.obtenerMenusRol());
        return "backoffice/inventario/GestionUsuario/rolenlaces/frmrolenlace";
    }

    @GetMapping(value = "/rolenlace-list")
    @ResponseBody
    public List<RolMenuDTO> listado() {
        return rolEnlaceService.obtenerMenusRol();
    }

    @PostMapping(value = "/rolenlace-registrar")
    public String rolEnlaceRegistrar(@ModelAttribute("re") RolEnlace rolEnlace,
                                     @RequestParam("idmenus") List<Integer> idmenus,
                                     @RequestParam(value = "actualizar", required = false, defaultValue = "false") boolean actualizar,
                                     Model model) {
        try{
            if(actualizar) {
                rolEnlaceService.asignarMenusARolUpdate(rolEnlace.getRol().getIdrol(),idmenus);
            }else {
                rolEnlaceService.asignarMenusARol(rolEnlace.getRol().getIdrol(), idmenus);
            }
            model.addAttribute("re", new RolEnlace());
        }catch (Exception e) {
            System.out.println("Hubo un error rol enlace registrar : " + e.getCause().getMessage());
        }
        return "backoffice/inventario/GestionUsuario/rolenlaces/frmrolenlace";
    }

    @GetMapping(value = "/rolenlace/{idrol}")
    @ResponseBody
    public RolMenuIdRolDTO buscarRolEnlace(@PathVariable Integer idrol) {
        return rolEnlaceService.obtenerMenusRolxIdRol(idrol);
    }
}
