package pe.tecnostore.tecnostore.model.dto.object.gestion.consultas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProveedorConsultaDTO {
    private Integer idproveedor;
    private String nomproveedor;
    private String nomempresa;
    private String correo;
    private String telefono;
    private LocalDate registro;
}
