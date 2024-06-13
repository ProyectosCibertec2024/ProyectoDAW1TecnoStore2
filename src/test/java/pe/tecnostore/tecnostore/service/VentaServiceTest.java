package pe.tecnostore.tecnostore.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.tecnostore.tecnostore.service.interfaces.IVentasService;

@SpringBootTest
public class VentaServiceTest {

    @Autowired
    private IVentasService ventasService;

    @Test
    void calcularNumeroVenta() {
        String numventa = ventasService.generarNumeroVenta();
        Assertions.assertEquals(numventa, "V00002");
    }
}
