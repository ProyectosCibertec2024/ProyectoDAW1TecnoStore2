package pe.tecnostore.tecnostore.service.interfaces.gestion.consultas;

import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.ProveedorConsultaDTO;

import java.util.List;

public interface IGestionConsultaProveedorService {
    List<ProveedorConsultaDTO> consultaProveedorxNomprov(String nomproveedor);
}
