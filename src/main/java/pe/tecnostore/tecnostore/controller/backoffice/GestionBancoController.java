package pe.tecnostore.tecnostore.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.tecnostore.tecnostore.model.bd.Empresa;
import pe.tecnostore.tecnostore.model.dto.object.gestion.bancos.TipoBancoEmpresaDTO;
import pe.tecnostore.tecnostore.service.interfaces.IEmpresaService;
import pe.tecnostore.tecnostore.service.interfaces.IGestionBancoService;

import java.util.List;

@SessionAttributes({"ENLACES","USUARIO"})

@AllArgsConstructor
@Controller
public class GestionBancoController {

    private IGestionBancoService gestionBancoService;
    private IEmpresaService empresaService;

    @GetMapping(value = "/gestionbancos")
    public String gestionBancos(Model model) {
        model.addAttribute("empresagestion", empresaService.empresaList());
        model.addAttribute("tipobancogestion", gestionBancoService.listadoTipoBanco());
        model.addAttribute("gestbancos", new TipoBancoEmpresaDTO());
        model.addAttribute("gesbancos", gestionBancoService.listadoBancosToEmpresa());

        return "backoffice/inventario/GestionBancos/frmgestionbancos";
    }

    @PostMapping(value = "/gestionbancos")
    public String gestionBancosRegistrar(@ModelAttribute("gestbancos") TipoBancoEmpresaDTO tipoBancoEmpresaDTO,
                                         @RequestParam("idtipobancos") List<Integer> idtipobancos,
                                         @RequestParam("actualizar") boolean actualizar,
                                         Model model) {
        try {
            if(actualizar) {
                gestionBancoService.actualizarBancosToEmpresa(tipoBancoEmpresaDTO.getIdempresa(), idtipobancos);
            }else {
                gestionBancoService.registrarBancosEmpresa(tipoBancoEmpresaDTO.getIdempresa(), idtipobancos);
            }
            model.addAttribute("gestbancos", new TipoBancoEmpresaDTO());
        }catch (Exception e) {
            System.out.println("Error en : " + e.getMessage());
            return "backoffice/inventario/GestionBancos/frmgestionbancos";
        }
        return "redirect:/gestionbancos";
    }

    @GetMapping(value = "/gestionbancos/{idempresa}")
    @ResponseBody
    public Empresa buscarEmpresaToBanco(@PathVariable Integer idempresa) {
        return empresaService.buscarEmpresa(idempresa);
    }
}
