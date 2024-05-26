package pe.tecnostore.tecnostore.model.bd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    private Integer idcategoria;
    private String descripcion;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria", targetEntity = Producto.class)
    private List<Producto> lstCategoria;
}
