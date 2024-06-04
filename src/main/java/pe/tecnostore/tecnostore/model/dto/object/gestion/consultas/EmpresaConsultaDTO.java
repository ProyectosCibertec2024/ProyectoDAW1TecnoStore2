package pe.tecnostore.tecnostore.model.dto.object.gestion.consultas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpresaConsultaDTO {
    private Integer idempresa;
    private String nomempresa;
    private String tipoempresa;
    private String bancos;
    private String telefono;
    private String email;
    private LocalDate registro;
}
