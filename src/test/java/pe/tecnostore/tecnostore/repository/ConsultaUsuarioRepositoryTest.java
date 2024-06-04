package pe.tecnostore.tecnostore.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.UsuarioConsultaDTO;
import pe.tecnostore.tecnostore.repository.gestion.consultas.GestionConsultaUsuarioRepository;

import java.util.List;

@SpringBootTest
public class ConsultaUsuarioRepositoryTest {

    @Autowired
    private GestionConsultaUsuarioRepository consultaUsuarioRepository;

    @Test
    void consultarUsuario() {
        List<UsuarioConsultaDTO> lista = consultaUsuarioRepository.consultaUsuario();

        for(UsuarioConsultaDTO u : lista) {
            System.out.println("IDUSUARIO : " + u.getIdusuario());
        }
    }

    @Test
    void consultarUsuarioxRol() {
        List<UsuarioConsultaDTO> lista2 = consultaUsuarioRepository.consultaUsuarioxRol(1);
        for(UsuarioConsultaDTO u : lista2) {
            System.out.println("IDUSUARIO : " + u.getIdusuario());
            System.out.println("USUARIOS : " + u.getNomusuario());
        }
    }
}
