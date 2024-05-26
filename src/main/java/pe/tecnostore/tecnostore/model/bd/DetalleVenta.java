package pe.tecnostore.tecnostore.model.bd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detalleventa")
public class DetalleVenta {
    @Id
    private Integer iddetalleventa;
    private Integer idventa;
    private Integer idproducto;
    private Integer cantidad;
    private Double precio;
    private Double total;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idventa", insertable = false, updatable = false,
    foreignKey = @ForeignKey(name = "FkIdVentaToVenta"))
    private Venta venta;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idproducto", insertable = false, updatable = false,
    foreignKey = @ForeignKey(name = "FkIdProductoToProducto"))
    private Producto producto;
}
