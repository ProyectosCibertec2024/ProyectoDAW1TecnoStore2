package pe.tecnostore.tecnostore.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.tecnostore.tecnostore.model.dto.object.RolMenuDTO;
import pe.tecnostore.tecnostore.model.dto.object.RolMenuIdRolDTO;

import java.util.List;

@SpringBootTest
public class RolEnlaceRepositoryTest {

    @Autowired
    private RolEnlaceRepository rolEnlaceRepository;

    @Test
    void testConsulta() {
        List<RolMenuDTO> lista = rolEnlaceRepository.obtenerMenusRol();

        for (RolMenuDTO list : lista) {
            System.out.println("Idrol : " + list.getIdrol());
            System.out.println("Descripcion Rol : " + list.getDescripcionrol());
            System.out.println("Descripcion Menu : " + list.getDescripcionmenus());
        }
    }

    @Test
    void testConsultaIdRol() {
        RolMenuIdRolDTO list = rolEnlaceRepository.obtenerMenusRolxIdRol(1);
        System.out.println(list.getIdrol());
        System.out.println(list.getDescripcionrol());
        System.out.println(list.getDescripcionmenus());
        System.out.println(list.getIdenlace());
    }

}
