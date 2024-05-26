package pe.tecnostore.tecnostore.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespuestaResponse {
    private boolean resultado;
    private String mensaje;
}
