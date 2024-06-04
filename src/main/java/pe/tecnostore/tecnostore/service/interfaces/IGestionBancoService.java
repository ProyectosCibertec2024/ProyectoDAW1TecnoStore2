package pe.tecnostore.tecnostore.service.interfaces;

import pe.tecnostore.tecnostore.model.bd.TipoBanco;
import pe.tecnostore.tecnostore.model.dto.object.gestion.bancos.TipoBancoEmpresaListDTO;

import java.util.List;

public interface IGestionBancoService {
    List<TipoBancoEmpresaListDTO> listadoBancosToEmpresa();
    List<TipoBanco> listadoTipoBanco();
    void registrarBancosEmpresa(int idEmpresa, List<Integer> idTiposBanco);
    void actualizarBancosToEmpresa(int idempresa, List<Integer> idtipobanco);
}
