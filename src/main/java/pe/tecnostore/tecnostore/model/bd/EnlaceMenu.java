package pe.tecnostore.tecnostore.model.bd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_enlace_menu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnlaceMenu implements Serializable {
    @Id
    private Integer idenlace;
    private String descripcion;
    private String ruta;
    private String icon;

    @JsonIgnore
    @OneToMany(mappedBy = "enlace")
    private List<RolEnlace> listarolenlace;

}
