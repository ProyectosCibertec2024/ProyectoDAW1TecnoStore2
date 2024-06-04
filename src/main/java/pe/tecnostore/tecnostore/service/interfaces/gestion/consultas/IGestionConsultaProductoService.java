package pe.tecnostore.tecnostore.service.interfaces.gestion.consultas;

import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.ProductoConsultaDTO;

import java.util.List;

public interface IGestionConsultaProductoService {
    List<ProductoConsultaDTO> consultaProductoXMarca(String nomproducto);
}
