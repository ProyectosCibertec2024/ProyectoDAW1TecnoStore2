package pe.tecnostore.tecnostore.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.EmpresaConsultaDTO;
import pe.tecnostore.tecnostore.repository.gestion.consultas.GestionConsultaEmpresaRepository;

import java.util.List;

@SpringBootTest
public class GestionConsultaEmpresaRepositoryTest {

    @Autowired
    private GestionConsultaEmpresaRepository empresaRepository;

    @Test
    void consultarEmpresaxTipoEmpresa() {
        List<EmpresaConsultaDTO> lista = empresaRepository.consultaEmpresa(1);
        for (EmpresaConsultaDTO e : lista) {
            System.out.println("BANCOS: " + e.getBancos());
        }
    }

}
