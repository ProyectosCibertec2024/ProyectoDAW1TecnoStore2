package pe.tecnostore.tecnostore.service.interfaces;

import pe.tecnostore.tecnostore.model.bd.Cliente;

import java.util.List;

public interface IClienteService {
    List<Cliente> listadoClientes();
    void guadarCliente(Cliente cliente);
    Cliente buscarCliente(int id);
    int obtenerIdCliente();
    void actualizarCliente(String nomcliente, String apecliente, String dnicliente,
                           String direccion, String telefono, Boolean activo, Integer idcliente);
}
