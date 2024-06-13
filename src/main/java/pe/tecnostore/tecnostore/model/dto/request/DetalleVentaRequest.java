package pe.tecnostore.tecnostore.model.dto.request;

import lombok.Data;

@Data
public class DetalleVentaRequest {
    private Integer iddetalleventa;
    private Integer idventa;
    private Integer idproducto;
    private String marca;
    private String categoria;
    private Integer cantidad;
    private Double precio;
    private Double subtotal;
}
