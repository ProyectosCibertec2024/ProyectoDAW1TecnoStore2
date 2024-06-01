package pe.tecnostore.tecnostore.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pe.tecnostore.tecnostore.service.interfaces.IEnlaceMenuService;

@AllArgsConstructor
@Controller
public class EnlaceMenuController {

    private IEnlaceMenuService enlaceMenuService;

    @GetMapping(value = "/menus-gestion")
    public String enlaceMenu(Model model) {
        model.addAttribute("menu", enlaceMenuService.listadoEnlaceMenus());
        return "backoffice/inventario/GestionUsuario/menus/frmenlacemenu";
    }
}
