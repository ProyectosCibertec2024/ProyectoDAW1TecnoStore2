package pe.tecnostore.tecnostore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.tecnostore.tecnostore.model.bd.Usuario;
import pe.tecnostore.tecnostore.service.interfaces.IUsuarioService;

import java.time.LocalDate;
import java.util.Date;


@SpringBootTest
class TecnostoreApplicationTests {

	@Autowired
	private IUsuarioService usuarioService;

	@Test
	void contextLoads() {
		try{
			Usuario u = new Usuario();
			u.setIdusuario(4);
			u.setNombre("Angel Santos");
			u.setUsername("angelito@gmail.com");
			u.setPassword("angel123");
			u.setRep_pass("angel123");
			u.setUrlfoto("https://firebasestorage.googleapis.com/v0/b/proyectoinventarioasp.appspot.com/o/users%2Fuser-angel.jpg?alt=media&token=35ed8c1d-5c00-4a60-9058-be3127ac0e8c");
			u.setNombrefoto("user-angel.jpg");
			LocalDate d = LocalDate.now();
            u.setFecharegistro(d);
			u.setDni("82717227");
			u.setIdrol(2);
			u.setActivo(false);
			usuarioService.guardarUsuario(u);
		}catch (Exception e) {
			System.out.println("Error en : " + e.getLocalizedMessage());
		}
	}

}
