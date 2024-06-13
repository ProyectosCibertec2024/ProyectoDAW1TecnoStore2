package pe.tecnostore.tecnostore.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.tecnostore.tecnostore.model.bd.Cliente;
import pe.tecnostore.tecnostore.model.bd.Venta;
import pe.tecnostore.tecnostore.model.dto.request.VentaRequest;
import pe.tecnostore.tecnostore.model.dto.response.RespuestaResponse;
import pe.tecnostore.tecnostore.service.interfaces.IClienteService;
import pe.tecnostore.tecnostore.service.interfaces.IUsuarioService;
import pe.tecnostore.tecnostore.service.interfaces.IVentasService;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@SessionAttributes({"ENLACES","USUARIO"})

@AllArgsConstructor
@Controller
public class VentasController {

    private IVentasService ventasService;
    private IClienteService clienteService;
    private IUsuarioService usuarioService;

    @GetMapping(value = "/ventas")
    public String ventas(Principal principal, Model model) {
        String name = principal.getName();
        System.out.println("NOMBRE USUARIO: " + name);
        int idusuario = usuarioService.obtenerIdUsuarioxNombre(name);
        List<Venta> venta = ventasService.consultaVentaxUsuario(idusuario);
        model.addAttribute("ventalist", venta);
        return "backoffice/ventas/frmventas";
    }

    @GetMapping(value = "/venta-list")
    @ResponseBody
    public List<Venta> listadoVenta(Principal principal) {
        String name = principal.getName();
        System.out.println("NOMBRE USUARIO: " + name);
        int idusuario = usuarioService.obtenerIdUsuarioxNombre(name);
        return ventasService.consultaVentaxUsuario(idusuario);
    }

    @GetMapping(value = "/idventa")
    @ResponseBody
    public int obtenerIdVenta() {
        return ventasService.obtenerIdVenta();
    }

    @GetMapping(value = "/numeroventa")
    @ResponseBody
    public String calcularNumeroVenta() {
        return ventasService.generarNumeroVenta();
    }

    @GetMapping(value = "/nomcliente-auto")
    @ResponseBody
    public List<Cliente> autocompletarCliente(@RequestParam String nomcliente) {
        return clienteService.obtenerClientexNombre(nomcliente);
    }

    @GetMapping(value = "/ventas/{idventa}")
    @ResponseBody
    public VentaRequest buscarVenta(@PathVariable Integer idventa) {
        Venta venta = ventasService.buscarVenta(idventa);
        Cliente cliente = clienteService.buscarCliente(venta.getIdcliente());
        VentaRequest ventaRequest = new VentaRequest();
        ventaRequest.setIdventa(venta.getIdventa());
        ventaRequest.setNumeroventa(venta.getNumeroventa());
        ventaRequest.setIdusuario(venta.getIdusuario());
        ventaRequest.setIdcliente(venta.getIdcliente());
        ventaRequest.setNomcliente(cliente.getNomcliente());
        ventaRequest.setApecliente(cliente.getApecliente());
        return ventaRequest;
    }

    @PostMapping(value = "/ventas")
    @ResponseBody
    public RespuestaResponse guardarVenta(@RequestBody Venta venta) {
        boolean rs = true;
        String men = "Guardado Exitosamente";
        try {
            int cod = ventasService.obtenerIdVenta();
            if(venta.getIdventa() < cod) {
                ventasService.actualizarventa(venta.getIdcliente(),venta.getIdventa());
            }else {
                LocalDate date = LocalDate.now();
                venta.setFecharegistro(date);
                venta.setTotal(0.0);
                ventasService.guardarVentas(venta);
            }
        }catch (Exception e) {
            System.out.println("Error en Guardar Venta: " + e.getMessage());
            rs = false;
            men = "Ups, Hubo Error : " + e.getMessage();
        }
        return RespuestaResponse.builder().resultado(rs).mensaje(men).build();
    }
}
