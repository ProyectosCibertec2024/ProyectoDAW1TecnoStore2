package pe.tecnostore.tecnostore.service.interfaces.gestion.consultas;

import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.UsuarioConsultaDTO;

import java.util.List;

public interface IGestionConsultaUsuarioService {
    List<UsuarioConsultaDTO> consultaUsuario();
    List<UsuarioConsultaDTO> consultaUsuarioxRol(int idrol);
}
