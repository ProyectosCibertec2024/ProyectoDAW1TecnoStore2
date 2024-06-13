package pe.tecnostore.tecnostore.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.tecnostore.tecnostore.model.bd.Cliente;

import java.util.List;

@SpringBootTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void clienteTest() {
        List<Cliente> list = clienteRepository.buscarPorNombreYApellido("Angel");
        for (Cliente c : list) {
            System.out.println("Apellido : " + c.getApecliente());
            System.out.println("Nombre : " + c.getNomcliente());
        }
    }
}
