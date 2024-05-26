package pe.tecnostore.tecnostore.service.interfaces;

import pe.tecnostore.tecnostore.model.bd.Empresa;

import java.util.List;

public interface IEmpresaService {
    List<Empresa> empresaList();
    void guardarEmpresa(Empresa empresa);
    Empresa buscarEmpresa(int id);
    int obtenerIdGenerado();
    void actualizarEmpresa(String nomempresa, String direccion, String telefono,
                                     Integer idtipoempresa, String email, String ruc, Boolean activo, Integer idempresa);
}
