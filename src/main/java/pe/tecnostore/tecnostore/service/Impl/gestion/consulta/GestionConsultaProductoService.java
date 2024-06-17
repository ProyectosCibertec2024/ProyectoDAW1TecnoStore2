package pe.tecnostore.tecnostore.service.Impl.gestion.consulta;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.ProductoConsultaDTO;
import pe.tecnostore.tecnostore.repository.gestion.consultas.GestionConsultaProductoRepository;
import pe.tecnostore.tecnostore.service.interfaces.gestion.consultas.IGestionConsultaProductoService;

import java.util.List;

@AllArgsConstructor
@Service
public class GestionConsultaProductoService implements IGestionConsultaProductoService {

    private GestionConsultaProductoRepository gestionConsultaProductoRepository;

    @Override
    public List<ProductoConsultaDTO> consultaProductoXMarca(String nomproducto) {
        String nom = nomproducto + "%";
        return gestionConsultaProductoRepository.consultaProductoXMarca(nom);
    }

    @Override
    public List<ProductoConsultaDTO> consultaProducto() {
        return gestionConsultaProductoRepository.consultaProducto();
    }
}
