package pe.tecnostore.tecnostore.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.tecnostore.tecnostore.model.bd.TipoEmpresa;
import pe.tecnostore.tecnostore.repository.TipoEmpresaRepository;
import pe.tecnostore.tecnostore.service.interfaces.ITipoEmpresaService;

import java.util.List;

@AllArgsConstructor
@Service
public class TipoEmpresaService implements ITipoEmpresaService {

    private TipoEmpresaRepository tipoEmpresaRepository;

    @Override
    public List<TipoEmpresa> listadoTipoEmpresas() {
        return tipoEmpresaRepository.findAll();
    }
}
