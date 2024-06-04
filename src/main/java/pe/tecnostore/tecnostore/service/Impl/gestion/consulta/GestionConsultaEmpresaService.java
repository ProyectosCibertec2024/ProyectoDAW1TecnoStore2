package pe.tecnostore.tecnostore.service.Impl.gestion.consulta;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.EmpresaConsultaDTO;
import pe.tecnostore.tecnostore.repository.gestion.consultas.GestionConsultaEmpresaRepository;
import pe.tecnostore.tecnostore.service.interfaces.gestion.consultas.IGestionConsultaEmpresaService;

import java.util.List;

@AllArgsConstructor
@Service
public class GestionConsultaEmpresaService implements IGestionConsultaEmpresaService {

    private GestionConsultaEmpresaRepository gestionConsultaEmpresaService;

    @Override
    public List<EmpresaConsultaDTO> consultaEmpresa(int idtipoempresa) {
        return gestionConsultaEmpresaService.consultaEmpresa(idtipoempresa);
    }
}
