package pe.tecnostore.tecnostore.model.bd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    private Integer idproducto;
    @Column(columnDefinition = "varchar(100)")
    @NotEmpty(message = "Campo Requerido")
    private String marca;
    private Integer idcategoria;
    @NotNull(message = "Campo Requerido")
    private Integer stock;
    private String urlimagen;
    private String nombreimagen;
    @NotNull(message = "Campo Requerido")
    private Double precio;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecharegistro;
    @NotEmpty(message = "Campo Requerido")
    private String color;
    @NotEmpty(message = "Campo Requerido")
    private String resolucion;
    @NotEmpty(message = "Campo Requerido")
    private String procesador;
    @NotNull(message = "Campo Requerido")
    private Double descuento;
    @NotEmpty(message = "Campo Requerido")
    private String ram;
    @NotEmpty(message = "Campo Requerido")
    private String espaciodisco;
    @NotEmpty(message = "Campo Requerido")
    @Column(name = "pantallatamaño")
    private String pantallatamanio;
    @NotNull(message = "Campo Requerido")
    private Integer idproveedor;
    private Boolean activo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idcategoria", insertable = false, updatable = false, foreignKey =
    @ForeignKey(name = "FkIdCategoriaToProducto"))
    private Categoria categoria;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idproveedor", insertable = false, updatable = false, foreignKey =
    @ForeignKey(name = "FkIdProveedorToProducto"))
    private Proveedor proveedor;

    @JsonIgnore
    @OneToMany(mappedBy = "producto", targetEntity = DetalleVenta.class)
    private List<DetalleVenta> detalleVentaList;
}
