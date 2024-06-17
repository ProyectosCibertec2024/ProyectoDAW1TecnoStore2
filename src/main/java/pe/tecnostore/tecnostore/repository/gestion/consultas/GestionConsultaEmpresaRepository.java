package pe.tecnostore.tecnostore.repository.gestion.consultas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.tecnostore.tecnostore.model.bd.Empresa;
import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.EmpresaConsultaDTO;

import java.util.List;

@Repository
public interface GestionConsultaEmpresaRepository extends JpaRepository<Empresa, Integer> {

    @Query("Select new pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.EmpresaConsultaDTO(e.idempresa, e.nomempresa, te.nomempresa, cast(group_concat(tbe.nombanco) as string), e.telefono, e.email, e.fecharegistro)" +
            " from Empresa e " +
            " join e.tipoEmpresa te" +
            " join e.tipoBancoList tbe" +
            " Where te.idtipoempresa = :idtipoempresa group by e.idempresa, e.nomempresa, e.telefono, e.email, e.fecharegistro")
    List<EmpresaConsultaDTO> consultaEmpresa(int idtipoempresa);

    @Query("Select new pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.EmpresaConsultaDTO(e.idempresa, e.nomempresa, te.nomempresa, cast(group_concat(tbe.nombanco) as string), e.telefono, e.email, e.fecharegistro)" +
            " from Empresa e " +
            " join e.tipoEmpresa te" +
            " join e.tipoBancoList tbe" +
            " Group by e.idempresa, e.nomempresa, te.nomempresa, e.telefono, e.email, e.fecharegistro")
    List<EmpresaConsultaDTO> consultarEmpresaSinParam();
}
