package pe.tecnostore.tecnostore.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.tecnostore.tecnostore.model.bd.Empresa;
import pe.tecnostore.tecnostore.repository.EmpresaRepository;
import pe.tecnostore.tecnostore.service.interfaces.IEmpresaService;

import java.util.List;

@AllArgsConstructor
@Service
public class EmpresaService implements IEmpresaService {

    private EmpresaRepository empresaRepository;

    @Override
    public List<Empresa> empresaList() {
        return empresaRepository.findAll();
    }

    @Override
    public void guardarEmpresa(Empresa empresa) {
        empresaRepository.save(empresa);
    }

    @Override
    public Empresa buscarEmpresa(int id) {
        return empresaRepository.findById(id).orElse(null);
    }

    @Override
    public int obtenerIdGenerado() {
        return empresaRepository.obtenerCod();
    }

    @Override
    public void actualizarEmpresa(String nomempresa, String direccion, String telefono, Integer idtipoempresa, String email, String ruc, Boolean activo, Integer idempresa) {
        empresaRepository.actualizarEmpresa(nomempresa, direccion, telefono, idtipoempresa, email, ruc, activo, idempresa);
    }
}
