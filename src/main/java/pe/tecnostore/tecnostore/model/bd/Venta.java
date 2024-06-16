package pe.tecnostore.tecnostore.model.bd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "venta")
public class Venta {
    @Id
    private Integer idventa;
    private String numeroventa;
    private Integer idusuario;
    private Integer idcliente;
    private Double total;
    private LocalDate fecharegistro;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idusuario", insertable = false, updatable = false,
                foreignKey = @ForeignKey(name = "FkIdUsuarioToVenta"))
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcliente", insertable = false, updatable = false,
                foreignKey = @ForeignKey(name = "FkIdClienteToCliente"))
    private Cliente cliente;

    @JsonIgnore
    @OneToMany(mappedBy = "venta", targetEntity = DetalleVenta.class)
    private List<DetalleVenta> detalleVentaList;

    @JsonIgnore
    @OneToMany(mappedBy = "ventafactura", cascade = CascadeType.ALL)
    private List<Factura> facturaList;
}
