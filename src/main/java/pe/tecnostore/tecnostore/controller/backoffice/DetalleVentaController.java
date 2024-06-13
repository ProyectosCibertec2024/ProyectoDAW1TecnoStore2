package pe.tecnostore.tecnostore.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.tecnostore.tecnostore.model.bd.DetalleVenta;
import pe.tecnostore.tecnostore.model.bd.Producto;
import pe.tecnostore.tecnostore.model.bd.Venta;
import pe.tecnostore.tecnostore.model.dto.request.DetalleVentaRequest;
import pe.tecnostore.tecnostore.model.dto.response.RespuestaResponse;
import pe.tecnostore.tecnostore.service.interfaces.IDetalleVentaService;
import pe.tecnostore.tecnostore.service.interfaces.IProductoService;
import pe.tecnostore.tecnostore.service.interfaces.IVentasService;

import java.util.List;

@SessionAttributes({"ENLACES","USUARIO"})

@AllArgsConstructor
@Controller
public class DetalleVentaController {

    private IDetalleVentaService detalleVentaService;
    private IVentasService ventasService;
    private IProductoService productoService;

    @GetMapping(value = "/venta-detalle/{idventa}")
    public String detalleVenta(@PathVariable Integer idventa, Model model) {
        Venta venta = ventasService.buscarVenta(idventa);
        model.addAttribute("producto", productoService.listaProductos());
        model.addAttribute("ventas", venta);
        model.addAttribute("detallelist", detalleVentaService.obtenerIdVentaToDetalleVenta(idventa));
        return "backoffice/ventas/frmdetalleventa";
    }

    @GetMapping(value = "/iddetalleventa")
    @ResponseBody
    public int obtenerIdDetalleVenta() {
        return detalleVentaService.obtenerIdDetalleVenta();
    }

    @GetMapping(value = "/detalle-venta/{iddetalleventa}")
    @ResponseBody
    public DetalleVentaRequest buscarDetalleVenta(@PathVariable Integer iddetalleventa) {
        DetalleVentaRequest detalleVentaRequest = null;
        try {
            DetalleVenta detalleVenta = detalleVentaService.buscarDetalleVenta(iddetalleventa);
            Producto producto = productoService.buscarProducto(detalleVenta.getIdproducto());
            detalleVentaRequest = new DetalleVentaRequest();
            detalleVentaRequest.setIddetalleventa(detalleVenta.getIddetalleventa());
            detalleVentaRequest.setIdventa(detalleVenta.getIdventa());
            detalleVentaRequest.setIdproducto(detalleVentaRequest.getIdproducto());
            detalleVentaRequest.setMarca(producto.getMarca());
            detalleVentaRequest.setCategoria(producto.getCategoria().getDescripcion());
            detalleVentaRequest.setCantidad(detalleVenta.getCantidad());
            detalleVentaRequest.setPrecio(detalleVenta.getPrecio());
            detalleVentaRequest.setSubtotal(detalleVenta.getSubtotal());
        } catch (Exception e) {
            System.out.println("Error En Buscar Detalle Venta : " + e.getMessage());
        }
        return detalleVentaRequest;
    }

    @GetMapping(value = "/detalle-venta-list/{idventa}")
    @ResponseBody
    public List<DetalleVenta> listadoDetalleVentas(@PathVariable Integer idventa) {
        return detalleVentaService.obtenerIdVentaToDetalleVenta(idventa);
    }

    @PostMapping(value = "/venta-detalle")
    @ResponseBody
    public RespuestaResponse guardarDetalleVenta(@RequestBody DetalleVenta detalleVenta) {
        boolean rs = true;
        String men = "Guardado Exitosamente";
        try{
            int cod = detalleVentaService.obtenerIdDetalleVenta();
            if(detalleVenta.getIddetalleventa() < cod) {
                detalleVentaService.guardarDetalleVenta(detalleVenta);
            }else {
                detalleVentaService.guardarDetalleVenta(detalleVenta);
            }
        }catch (Exception e) {
            System.out.println("Error en Guardar Detalle Venta: " + e.getMessage());
        }
        return RespuestaResponse.builder().mensaje(men).resultado(rs).build();
    }

    @PostMapping("/actualizar-venta/{idventa}")
    @ResponseBody
    public void actualizarVenta(@PathVariable Integer idventa) {
        try {
            detalleVentaService.actualizarVentaTotal(idventa);
        }catch (Exception e) {
            System.out.println("Error en Actualizar Venta : " + e.getMessage());
        }
    }
}
