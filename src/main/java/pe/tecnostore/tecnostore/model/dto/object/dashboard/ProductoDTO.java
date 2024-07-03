package pe.tecnostore.tecnostore.model.dto.object.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductoDTO {
    private Integer idproducto;
    private String marca;
    private Long totalVendido;
}
