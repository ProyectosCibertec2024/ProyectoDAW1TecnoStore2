package pe.tecnostore.tecnostore.model.bd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_roles")
public class Rol {
    @Id
    private Integer idrol;
    private String descripcion;

    @JsonIgnore
    @OneToMany(mappedBy = "rol")
    private List<Usuario> listaU;

    @JsonIgnore
    @OneToMany(mappedBy = "rol")
    private List<RolEnlace> listarolEnlace;

}