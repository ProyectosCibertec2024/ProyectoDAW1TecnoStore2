package pe.tecnostore.tecnostore.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.tecnostore.tecnostore.model.bd.EnlaceMenu;
import pe.tecnostore.tecnostore.model.dto.response.RespuestaResponse;
import pe.tecnostore.tecnostore.service.interfaces.IEnlaceMenuService;

import java.util.List;

@SessionAttributes({"ENLACES","USUARIO"})

@AllArgsConstructor
@Controller
public class EnlaceMenuController {

    private IEnlaceMenuService enlaceMenuService;

    @GetMapping(value = "/menus-gestion")
    public String enlaceMenu(Model model) {
        model.addAttribute("menu", enlaceMenuService.listadoEnlaceMenus());
        return "backoffice/inventario/GestionUsuario/menus/frmenlacemenu";
    }

    @GetMapping(value = "/idenlacemenu")
    @ResponseBody
    public int obtenerIdEnlaceMenu() {
        return enlaceMenuService.obtenerIdEnlaceMenu();
    }

    @GetMapping(value = "/menus-gestion-list")
    @ResponseBody
    public List<EnlaceMenu> listaEnlaceMenus() {
        return enlaceMenuService.listadoEnlaceMenus();
    }

    @PostMapping(value = "/menu-gestion")
    @ResponseBody
    public RespuestaResponse registrarEnlaceMenu(@RequestBody EnlaceMenu enlaceMenu) {
        boolean rs = true;
        String m = "Guardado Existosamente";
        try {
            int cod = enlaceMenuService.obtenerIdEnlaceMenu();
            if(enlaceMenu.getIdenlace() < cod) {
                enlaceMenuService.guardarEnlaceMenus(enlaceMenu);
            }else {
                enlaceMenuService.guardarEnlaceMenus(enlaceMenu);
            }
        }catch (Exception e) {
            rs = false;
            m = "Hubo un error: " + e.getCause().getMessage();
        }
        return RespuestaResponse.builder().resultado(rs).mensaje(m).build();
    }

    @GetMapping(value = "/menu-gestion/{idenlacemenu}")
    @ResponseBody
    public EnlaceMenu buscarEnlaceMenu(@PathVariable Integer idenlacemenu) {
        return enlaceMenuService.buscarEnlaceMenu(idenlacemenu);
    }
}
