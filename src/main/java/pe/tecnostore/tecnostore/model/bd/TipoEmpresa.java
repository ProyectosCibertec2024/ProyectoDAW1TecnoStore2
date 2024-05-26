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
@Table(name = "tipo_empresa")
public class TipoEmpresa {
    @Id
    private Integer idtipoempresa;
    private String nomempresa;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoEmpresa")
    private List<Empresa> empresaList;
}
