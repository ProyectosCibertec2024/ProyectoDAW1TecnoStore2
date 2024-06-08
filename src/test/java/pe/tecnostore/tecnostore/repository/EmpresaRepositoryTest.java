package pe.tecnostore.tecnostore.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmpresaRepositoryTest {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Test
    void contarEmpresa() {
        int count = empresaRepository.obtenerCantidadEmpresa();
        System.out.println("Cantidad Empresa : "+ count);
    }

}
