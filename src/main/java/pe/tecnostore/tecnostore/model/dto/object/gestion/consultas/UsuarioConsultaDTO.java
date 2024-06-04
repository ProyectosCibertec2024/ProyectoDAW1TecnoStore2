package pe.tecnostore.tecnostore.model.dto.object.gestion.consultas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioConsultaDTO {
    private Integer idusuario;
    private String nomusuario;
    private LocalDate fecharegistro;
    private String nomrol;
    private String enlacesmenus;
    private String foto;
}
