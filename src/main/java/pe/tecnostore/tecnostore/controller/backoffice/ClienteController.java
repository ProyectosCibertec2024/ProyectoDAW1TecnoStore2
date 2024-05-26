package pe.tecnostore.tecnostore.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.tecnostore.tecnostore.model.bd.Cliente;
import pe.tecnostore.tecnostore.model.dto.response.RespuestaResponse;
import pe.tecnostore.tecnostore.service.interfaces.IClienteService;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Controller
public class ClienteController {

    private IClienteService clienteService;

    @GetMapping(value = "/cliente")
    public String cliente(Model model) {
        model.addAttribute("cliente", clienteService.listadoClientes());
        return "backoffice/inventario/GestionInventario/cliente/frmcliente";
    }

    @GetMapping(value = "/cliente-list")
    @ResponseBody
    public List<Cliente> clienteLista() {
        return clienteService.listadoClientes();
    }

    @GetMapping(value = "/cliente/{idcliente}")
    @ResponseBody
    public Cliente buscarCliente(@PathVariable Integer idcliente) {
        return clienteService.buscarCliente(idcliente);
    }

    @PostMapping(value = "/cliente")
    @ResponseBody
    public RespuestaResponse guardarCliente(@RequestBody Cliente cliente) {
        boolean rs = true;
        String men = "Guardado Exitosamente";
        try {
            if(cliente.getIdcliente() != null) {
                clienteService.actualizarCliente(cliente.getNomcliente(), cliente.getApecliente(),
                        cliente.getDnicliente(), cliente.getDireccion(), cliente.getTelefono(),
                        cliente.getActivo(), cliente.getIdcliente());
            }else {
                LocalDate fecha = LocalDate.now();
                cliente.setFecharegistro(fecha);
                clienteService.guadarCliente(cliente);
            }
        }catch (Exception e) {
            rs = false;
            men = "Ups, Hubo un error en : " + e.getCause().getMessage();
        }
        return RespuestaResponse.builder().resultado(rs).mensaje(men).build();
    }

    @GetMapping(value = "/idcliente")
    @ResponseBody
    public Integer obtenerIdCliente() {
        return clienteService.obtenerIdCliente();
    }
}
