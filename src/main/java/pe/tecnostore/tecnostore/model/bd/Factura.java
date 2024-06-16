package pe.tecnostore.tecnostore.model.bd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "factura")
public class Factura {
    @Id
    private Integer idfactura;
    private Integer idventa;
    private LocalDate fechaemision;
    private Integer numerofactura;
    private Boolean estado;
    private String urlfactura;
    private String nomfactura;

    @ManyToOne
    @JoinColumn(name = "idventa", insertable = false, updatable = false)
    private Venta ventafactura;
}
