package pe.tecnostore.tecnostore.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"ENLACES","USUARIO"})

@AllArgsConstructor
@Controller
public class VentasController {

    @GetMapping(value = "/")
    public String ventas() {
        return "backoffice/ventas/frmventas";
    }
}
