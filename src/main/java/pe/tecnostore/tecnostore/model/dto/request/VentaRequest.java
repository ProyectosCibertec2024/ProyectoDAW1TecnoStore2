package pe.tecnostore.tecnostore.model.dto.request;

import lombok.Data;

@Data
public class VentaRequest {
    private Integer idventa;
    private String numeroventa;
    private Integer idusuario;
    private Integer idcliente;
    private String nomcliente;
    private String apecliente;
}
