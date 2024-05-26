package pe.tecnostore.tecnostore.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.tecnostore.tecnostore.model.bd.Proveedor;
import pe.tecnostore.tecnostore.model.dto.response.RespuestaResponse;
import pe.tecnostore.tecnostore.service.interfaces.IProveedorService;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Controller
public class ProveedorController {

    private IProveedorService proveedorService;

    @GetMapping(value = "/proveedor")
    public String proveedor(Model model) {
        model.addAttribute("proveedor", proveedorService.listaProveedores());
        return "backoffice/inventario/GestionInventario/proveedor/frmproveedor";
    }

    @GetMapping(value = "/proveedor-list")
    @ResponseBody
    public List<Proveedor> listaProveedores() {
        return proveedorService.listaProveedores();
    }

    @PostMapping(value = "/proveedor")
    @ResponseBody
    public RespuestaResponse guardarProveedor(@RequestBody Proveedor proveedor) {
        boolean rs = true;
        String men = "Guardado Exitosmente";
        try {
            int cod = proveedorService.obtenerIdProveedor();
            if(proveedor.getIdproveedor() < cod) {
                proveedorService.actualizarProveedor(proveedor.getNomproveedor(),
                        proveedor.getTelefono(),proveedor.getCorreo(),proveedor.getEstado(),
                        proveedor.getIdempresa(), proveedor.getIdproveedor());
            }else {
                LocalDate fecha = LocalDate.now();
                proveedor.setFecharegistro(fecha);
                proveedorService.guardarProveedor(proveedor);
            }
        } catch (Exception e) {
            rs = false;
            men = "Ups, Hubo un error: " + e.getCause().getMessage();
        }
        return RespuestaResponse.builder().resultado(rs).mensaje(men).build();
    }

    @GetMapping(value = "/proveedor/{idproveedor}")
    @ResponseBody
    public Proveedor buscarProveedor(@PathVariable Integer idproveedor) {
        return proveedorService.buscarProveedor(idproveedor);
    }

    @GetMapping(value = "/idproveedor")
    @ResponseBody
    public Integer obtenerIdProveedor() {
        return proveedorService.obtenerIdProveedor();
    }
}
