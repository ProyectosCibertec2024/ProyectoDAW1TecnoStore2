package pe.tecnostore.tecnostore.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.tecnostore.tecnostore.model.dto.object.TipoBancoEmpresaListDTO;

import java.util.List;

@SpringBootTest
public class GestionBancosRepositoryTest {

    @Autowired
    private GestionBancoRepository gestionBancoRepository;

    @Test
    void TestGestionBancos(){
        List<TipoBancoEmpresaListDTO> lista = gestionBancoRepository.listadoBancosToEmpresa();
        Assertions.assertNotNull(lista);
        assert (!lista.isEmpty());
        for (TipoBancoEmpresaListDTO l : lista) {
            System.out.println("EMPRESA: " + l.getNomempresa());
            System.out.println("BANCOS: " + l.getNombanco());
            System.out.println("IDTIPOBANCO: " + l.getIdtipobanco());
        }
    }

    @Test
    void consultaBancosXIdEmpresa() {
        List<Integer> lista = gestionBancoRepository.consultaxIdEmpresa(1);

        for (Integer l : lista) {
            System.out.println(l);
        }
    }
}
