package pe.tecnostore.tecnostore.model.bd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tipoproveedor")
public class TipoProveedor {
    @Id
    private Integer idtipoproveedor;
    private String nomtipoprov;

    @JsonIgnore
    @ManyToMany(mappedBy = "tipoProveedors")
    private List<Proveedor> proveedorList;
}
