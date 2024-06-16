package pe.tecnostore.tecnostore.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.tecnostore.tecnostore.model.dto.object.reportes.FacturaDTO;
import pe.tecnostore.tecnostore.service.interfaces.IFacturaService;

import java.util.List;

@SpringBootTest
public class FacturaRepositoryTest {

    @Autowired
    private IFacturaService facturaService;

    @Test
    void consultar() {
        List<FacturaDTO> factura = facturaService.consultarFacturaIdVenta(1);
        for (FacturaDTO f : factura) {
            System.out.println(f.getNumerofactura());
            System.out.println(f.getApecliente());
            System.out.println(f.getNomcliente());
        }
    }
}
