package pe.tecnostore.tecnostore.repository.gestion.consultas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.tecnostore.tecnostore.model.bd.Proveedor;
import pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.ProveedorConsultaDTO;

import java.util.List;

@Repository
public interface GestionConsultaProveedorRepository extends JpaRepository<Proveedor, Integer> {
    @Query("SELECT new pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.ProveedorConsultaDTO(pr.idproveedor, pr.nomproveedor, e.nomempresa, pr.correo, pr.telefono, pr.fecharegistro)" +
            " FROM Proveedor pr" +
            " JOIN pr.empresa e" +
            " WHERE pr.nomproveedor LIKE :nomproveedor " +
            " GROUP BY pr.idproveedor, pr.nomproveedor, e.nomempresa, pr.correo, pr.telefono, pr.fecharegistro")
    List<ProveedorConsultaDTO> consultaProveedorxNomprov(String nomproveedor);

    @Query("SELECT new pe.tecnostore.tecnostore.model.dto.object.gestion.consultas.ProveedorConsultaDTO(pr.idproveedor, pr.nomproveedor, e.nomempresa, pr.correo, pr.telefono, pr.fecharegistro)" +
            " FROM Proveedor pr" +
            " JOIN pr.empresa e" +
            " GROUP BY pr.idproveedor, pr.nomproveedor, e.nomempresa, pr.correo, pr.telefono, pr.fecharegistro")
    List<ProveedorConsultaDTO> consultaProveedor();
}
