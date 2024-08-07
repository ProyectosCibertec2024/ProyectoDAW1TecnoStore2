package pe.tecnostore.tecnostore.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.tecnostore.tecnostore.model.dto.object.dashboard.ProductoDTO;
import pe.tecnostore.tecnostore.model.dto.object.dashboard.VentaDTO;
import pe.tecnostore.tecnostore.service.interfaces.IProductoService;
import pe.tecnostore.tecnostore.service.interfaces.IVentasService;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Controller
public class DashboardController {

    private IVentasService ventasService;
    private IProductoService productoService;

    @GetMapping(value = "/dashboard-ventas")
    @ResponseBody
    public List<VentaDTO> ventasxMes() {
        LocalDate fin = LocalDate.now();
        LocalDate inicio = fin.minusDays(30);
        return ventasService.obtnerVentasxMes(inicio, fin);
    }

    @GetMapping(value = "/dashboard-ventas-productos")
    @ResponseBody
    public List<ProductoDTO> productoxmasvendidos() {
        return productoService.productosmasvendidos();
    }
}
