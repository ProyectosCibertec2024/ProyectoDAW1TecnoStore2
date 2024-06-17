package pe.tecnostore.tecnostore.service.Impl.gestion.consulta;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.ProveedorConsultaDTO;
import pe.tecnostore.tecnostore.repository.gestion.consultas.GestionConsultaProveedorRepository;
import pe.tecnostore.tecnostore.service.interfaces.gestion.consultas.IGestionConsultaProveedorService;

import java.util.List;

@AllArgsConstructor
@Service
public class GestionConsultaProveedorService implements IGestionConsultaProveedorService {

    private GestionConsultaProveedorRepository proveedorRepository;

    @Override
    public List<ProveedorConsultaDTO> consultaProveedorxNomprov(String nomproveedor) {
        String nom = nomproveedor + "%";
        return proveedorRepository.consultaProveedorxNomprov(nom);
    }

    @Override
    public List<ProveedorConsultaDTO> consultaProveedor() {
        return proveedorRepository.consultaProveedor();
    }
}
