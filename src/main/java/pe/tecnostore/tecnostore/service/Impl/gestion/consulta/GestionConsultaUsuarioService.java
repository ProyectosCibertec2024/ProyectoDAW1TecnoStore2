package pe.tecnostore.tecnostore.service.Impl.gestion.consulta;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.UsuarioConsultaDTO;
import pe.tecnostore.tecnostore.repository.gestion.consultas.GestionConsultaUsuarioRepository;
import pe.tecnostore.tecnostore.service.interfaces.gestion.consultas.IGestionConsultaUsuarioService;

import java.util.List;

@AllArgsConstructor
@Service
public class GestionConsultaUsuarioService implements IGestionConsultaUsuarioService {

    private GestionConsultaUsuarioRepository gestionConsultaUsuarioRepository;

    @Override
    public List<UsuarioConsultaDTO> consultaUsuario() {
        return gestionConsultaUsuarioRepository.consultaUsuario();
    }

    @Override
    public List<UsuarioConsultaDTO> consultaUsuarioxRol(int idrol) {
        return gestionConsultaUsuarioRepository.consultaUsuarioxRol(idrol);
    }
}
