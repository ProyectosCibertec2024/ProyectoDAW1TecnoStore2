package pe.tecnostore.tecnostore.model.bd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "proveedor")
public class Proveedor {
    @Id
    private Integer idproveedor;
    private String nomproveedor;
    private String telefono;
    private String correo;
    private Boolean estado;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecharegistro;
    private Integer idempresa;

    @JsonIgnore
    @OneToMany(mappedBy = "proveedor", targetEntity = Producto.class)
    private List<Producto> productoList;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idempresa", insertable = false, updatable = false,
    foreignKey = @ForeignKey(name = "FkIdEmpresaToEmpresa"))
    private Empresa empresa;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "tipoprov_proveedor", joinColumns = @JoinColumn(name = "idproveedor"),
            inverseJoinColumns = @JoinColumn(name = "idtipoproveedor"),
            foreignKey = @ForeignKey(name = "FkProveedorToManyTipoProvProv"),
            inverseForeignKey = @ForeignKey(name = "FkTipoProveedorManyTipoProvProv"))
    private List<TipoProveedor> tipoProveedors;
}
