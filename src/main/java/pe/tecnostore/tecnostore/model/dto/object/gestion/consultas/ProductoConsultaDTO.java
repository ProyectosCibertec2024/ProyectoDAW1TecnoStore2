package pe.tecnostore.tecnostore.model.dto.object.gestion.consultas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductoConsultaDTO {
    private Integer idproducto;
    private String marca;
    private String categoria;
    private Double precio;
    private String nomproveedor;
    private LocalDate registro;
    private String imagen;
}
