package pe.tecnostore.tecnostore.service.interfaces.gestion.consultas;

import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.EmpresaConsultaDTO;

import java.util.List;

public interface IGestionConsultaEmpresaService {
    List<EmpresaConsultaDTO> consultaEmpresa(int idtipoempresa);
    List<EmpresaConsultaDTO> consultarEmpresaSinParam();
}
