package pe.tecnostore.tecnostore.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.tecnostore.tecnostore.model.bd.Empresa;
import pe.tecnostore.tecnostore.model.bd.TipoEmpresa;
import pe.tecnostore.tecnostore.model.dto.response.RespuestaResponse;
import pe.tecnostore.tecnostore.service.interfaces.IEmpresaService;
import pe.tecnostore.tecnostore.service.interfaces.ITipoEmpresaService;

import java.time.LocalDate;
import java.util.List;

@SessionAttributes({"ENLACES","USUARIO"})

@AllArgsConstructor
@Controller
public class EmpresaController {

    private IEmpresaService empresaService;
    private ITipoEmpresaService tipoEmpresaService;

    @GetMapping(value = "/empresa")
    public String empresas(Model model) {
        model.addAttribute("empresa", empresaService.empresaList());
        return "backoffice/inventario/GestionInventario/empresa/frmempresa";
    }

    @GetMapping(value = "/empresa-list")
    @ResponseBody
    public List<Empresa> empresaList() {
        return empresaService.empresaList();
    }

    @PostMapping(value = "/empresa")
    @ResponseBody
    public RespuestaResponse registrarEmpresa(@RequestBody Empresa empresa) {
        boolean rs = true;
        String men = "Guardado Exitosamente";
        try {
            int cod = empresaService.obtenerIdGenerado();
            if (empresa.getIdempresa() < cod) {
                empresaService.actualizarEmpresa(empresa.getNomempresa(), empresa.getDireccion(),
                        empresa.getTelefono(), empresa.getIdtipoempresa(), empresa.getEmail(),
                        empresa.getRuc(), empresa.getActivo(), empresa.getIdempresa());
            } else {
                LocalDate fecha = LocalDate.now();
                empresa.setFecharegistro(fecha);
                empresaService.guardarEmpresa(empresa);
            }
        }catch (Exception e) {
            System.out.println("Error En Guardar Empresa : " + e.getCause().getMessage());
            men = "Algo fallo, Intente de nuevo";
            rs = false;
        }
        return RespuestaResponse.builder().resultado(rs).mensaje(men).build();
    }

    @GetMapping(value = "/empresa/{idempresa}")
    @ResponseBody
    public Empresa buscarEmpresa(@PathVariable Integer idempresa) {
        return empresaService.buscarEmpresa(idempresa);
    }

    /*Tipo Empresa - Listado*/
    @GetMapping(value = "/tipoempresa-list")
    @ResponseBody
    public List<TipoEmpresa> tipoEmpresaList() {
        return tipoEmpresaService.listadoTipoEmpresas();
    }

    @GetMapping(value = "/idempresa")
    @ResponseBody
    public Integer autogenerado() {
        return empresaService.obtenerIdGenerado();
    }
}
