package pe.tecnostore.tecnostore.model.dto.object.reportes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FacturaConsultaFechaDTO {
    private Integer numerofactura;
    private String numeroventa;
    private String nomcliente;
    private String apecliente;
    private String dnicliente;
    private String direccion;
    private String fonocliente;
    private LocalDate fechaemision;
}
