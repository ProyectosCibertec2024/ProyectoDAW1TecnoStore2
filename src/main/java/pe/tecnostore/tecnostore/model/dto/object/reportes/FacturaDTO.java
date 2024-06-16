package pe.tecnostore.tecnostore.model.dto.object.reportes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FacturaDTO {
    private Integer numerofactura;
    private Date fechaemision;
    private String nomproveedor;
    private String nomempresa;
    private String telefono;
    private String correo;
    private String dnicliente;
    private String nomcliente;
    private String apecliente;
    private String direccion;
    private String fonocliente;
    private Double subtotal;
    private Integer cantidad;
    private String marca;
    private String descripcion;
    private Double precio;
    private Double descuento;
    private Double total;
}
