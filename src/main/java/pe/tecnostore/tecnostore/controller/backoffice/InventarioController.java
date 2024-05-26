package pe.tecnostore.tecnostore.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"ENLACES","USUARIO"})

@AllArgsConstructor
@Controller
public class InventarioController {

    @GetMapping("/inventario")
    public String inventario(Model model){
        return "backoffice/inventario/inventario/inventario";
    }

    @GetMapping("/gestioninventario")
    public String gestionInventario(Model model){
        return "backoffice/inventario/GestionInventario/gestionInventario";
    }

}
