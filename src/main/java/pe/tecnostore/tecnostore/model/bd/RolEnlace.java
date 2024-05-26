package pe.tecnostore.tecnostore.model.bd;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "tb_rol_enlace")
public class RolEnlace {
    @EmbeddedId
    private RolEnlacePK pk;

    @ManyToOne
    @JoinColumn(name = "idrol", insertable = false, updatable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "idenlace", insertable = false, updatable = false)
    private EnlaceMenu enlace;

}