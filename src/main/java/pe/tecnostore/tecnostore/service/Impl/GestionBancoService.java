package pe.tecnostore.tecnostore.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.tecnostore.tecnostore.model.bd.TipoBanco;
import pe.tecnostore.tecnostore.model.dto.object.gestion.bancos.TipoBancoEmpresaListDTO;
import pe.tecnostore.tecnostore.repository.GestionBancoRepository;
import pe.tecnostore.tecnostore.repository.TipoBancoRepository;
import pe.tecnostore.tecnostore.service.interfaces.IGestionBancoService;

import java.util.List;

@AllArgsConstructor
@Service
public class GestionBancoService implements IGestionBancoService {

    private GestionBancoRepository gestionBancoRepository;
    private TipoBancoRepository tipoBancoRepository;

    @Override
    public List<TipoBancoEmpresaListDTO> listadoBancosToEmpresa() {
        return gestionBancoRepository.listadoBancosToEmpresa();
    }

    @Override
    public List<TipoBanco> listadoTipoBanco() {
        return tipoBancoRepository.findAll();
    }

    @Override
    public void registrarBancosEmpresa(int idEmpresa, List<Integer> idTiposBanco) {
        gestionBancoRepository.registrarBancosEmpresa(idEmpresa,idTiposBanco);
    }

    @Override
    public void actualizarBancosToEmpresa(int idempresa, List<Integer> idtipobanco) {
        gestionBancoRepository.actualizarBancosToEmpresa(idempresa, idtipobanco);
    }

}
