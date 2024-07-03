package pe.tecnostore.tecnostore.model.dto.object.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VentaDTO {
    private LocalDate fecharegistro;
    private Double total;
}
