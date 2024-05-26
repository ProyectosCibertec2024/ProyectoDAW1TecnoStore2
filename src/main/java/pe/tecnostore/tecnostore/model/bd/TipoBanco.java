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
@Table(name = "tipobanco")
public class TipoBanco {
    @Id
    private Integer idtipobanco;
    private String nombanco;

    @JsonIgnore
    @ManyToMany(mappedBy = "tipoBancoList")
    private List<Empresa> empresaList;
}
