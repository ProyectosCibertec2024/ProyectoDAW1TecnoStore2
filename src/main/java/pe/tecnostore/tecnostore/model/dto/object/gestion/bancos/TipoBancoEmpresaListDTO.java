package pe.tecnostore.tecnostore.model.dto.object.gestion.bancos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TipoBancoEmpresaListDTO {
    private Integer idempresa;
    private String nomempresa;
    private String nombanco;
    private String telefono;
    private String idtipobanco;
}
