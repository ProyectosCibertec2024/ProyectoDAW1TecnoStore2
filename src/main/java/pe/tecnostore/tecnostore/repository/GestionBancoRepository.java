package pe.tecnostore.tecnostore.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.tecnostore.tecnostore.model.bd.Empresa;
import pe.tecnostore.tecnostore.model.dto.object.TipoBancoEmpresaListDTO;

import java.util.List;

@Repository
public interface GestionBancoRepository extends JpaRepository<Empresa, Integer> {

    @Query("SELECT new pe.tecnostore.tecnostore.model.dto.object.TipoBancoEmpresaListDTO(e.idempresa,e.nomempresa, CAST(GROUP_CONCAT(tb.nombanco) as string ), e.telefono, CAST(GROUP_CONCAT(tb.idtipobanco) as string))" +
           "FROM Empresa e JOIN e.tipoBancoList tb GROUP BY e.idempresa, e.nomempresa, e.telefono")
    List<TipoBancoEmpresaListDTO> listadoBancosToEmpresa();

    @Transactional
    default void registrarBancosEmpresa(int idEmpresa, List<Integer> idTiposBanco) {
        for (Integer idTipoBanco : idTiposBanco) {
            insertarBancoEmpresa(idEmpresa, idTipoBanco);
        }
    }

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tipobanco_empresa WHERE idempresa = :idempresa AND idtipobanco IN :idTiposBanco", nativeQuery = true)
    void eliminarBancosEmpresa(@Param("idempresa") int idempresa, @Param("idTiposBanco") List<Integer> idTiposBanco);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO tipobanco_empresa (idempresa, idtipobanco) VALUES (:idempresa, :idtipobanco)", nativeQuery = true)
    void insertarBancoEmpresa(@Param("idempresa") int idempresa, @Param("idtipobanco") int idtipobanco);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO tipobanco_empresa (idempresa, idtipobanco) VALUES (:idempresa, :idtipobanco) ON DUPLICATE KEY UPDATE idtipobanco = CONCAT(idtipobanco, ',', :idtipobanco)", nativeQuery = true)
    void insertarBancoEmpresaUpdate(@Param("idempresa") int idempresa, @Param("idtipobanco") int idtipobanco);

    @Transactional
    default void actualizarBancosToEmpresa(int idempresa, List<Integer> idtipobanco) {
        List<Integer> tiposBancoActuales = consultaxIdEmpresa(idempresa);

        tiposBancoActuales.removeAll(idtipobanco);
        if (!tiposBancoActuales.isEmpty()) {
            eliminarBancosEmpresa(idempresa, tiposBancoActuales);
        }

        idtipobanco.removeAll(consultaxIdEmpresa(idempresa));
        for (Integer IdTipoBanco : idtipobanco) {
            insertarBancoEmpresaUpdate(idempresa, IdTipoBanco);
        }
    }

    @Transactional
    @Modifying
    @Query(value = "UPDATE tipobanco_empresa SET idtipobanco = :idtipobanco WHERE idempresa = :idempresa", nativeQuery = true)
    void actualizarBancoEmpresa(@Param("idempresa") int idempresa, @Param("idtipobanco") int idtipobanco);

    @Transactional
    @Query(value = "SELECT te.idtipobanco FROM tipobanco_empresa te WHERE te.idempresa = :idempresa", nativeQuery = true)
    List<Integer> consultaxIdEmpresa(Integer idempresa);
}
