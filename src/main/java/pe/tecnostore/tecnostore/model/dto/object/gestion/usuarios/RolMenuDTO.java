package pe.tecnostore.tecnostore.model.dto.object.gestion.usuarios;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RolMenuDTO {
    private Integer idrol;
    private String descripcionrol;
    private String descripcionmenus;
}
