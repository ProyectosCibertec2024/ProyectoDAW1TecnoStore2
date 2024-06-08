package pe.tecnostore.tecnostore.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.tecnostore.tecnostore.model.bd.Cliente;
import pe.tecnostore.tecnostore.repository.ClienteRepository;
import pe.tecnostore.tecnostore.service.interfaces.IClienteService;

import java.util.List;

@AllArgsConstructor
@Service
public class ClienteService implements IClienteService {

    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listadoClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public void guadarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public Cliente buscarCliente(int id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public int obtenerIdCliente() {
        return clienteRepository.obtenerIdCliente();
    }

    @Override
    public void actualizarCliente(String nomcliente, String apecliente, String dnicliente, String direccion, String telefono, Boolean activo, Integer idcliente) {
        clienteRepository.actualizarCliente(nomcliente, apecliente, dnicliente, direccion, telefono, activo, idcliente);
    }

    @Override
    public int obtenerCantidadCliente() {
        return clienteRepository.obtenerCantidadCliente();
    }
}
