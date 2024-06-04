package pe.tecnostore.tecnostore.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.ProveedorConsultaDTO;
import pe.tecnostore.tecnostore.repository.gestion.consultas.GestionConsultaProveedorRepository;

import java.util.List;

@SpringBootTest
public class GestionConsultaProveedorRepositoryTest {

    @Autowired
    private GestionConsultaProveedorRepository proveedorRepository;

    @Test
    void consultarProveedorxNomprov() {
        String nom = "V" + "%";
        List<ProveedorConsultaDTO> lista = proveedorRepository.consultaProveedorxNomprov(nom);
        for (ProveedorConsultaDTO p : lista) {
            System.out.println("Proveedores : " + p.getNomproveedor());
        }
    }
}
