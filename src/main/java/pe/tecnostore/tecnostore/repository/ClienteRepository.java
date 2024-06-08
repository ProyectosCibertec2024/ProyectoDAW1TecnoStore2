package pe.tecnostore.tecnostore.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.tecnostore.tecnostore.model.bd.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query(value = "SELECT coalesce(max(c.idcliente), 0 ) + 1 FROM cliente c", nativeQuery = true)
    int obtenerIdCliente();

    @Modifying
    @Transactional
    @Query("UPDATE Cliente set nomcliente=:nomcliente, apecliente=:apecliente, " +
            "dnicliente=:dnicliente, direccion=:direccion, telefono=:telefono, activo=:activo " +
            "WHERE idcliente=:idcliente")
    void actualizarCliente(String nomcliente, String apecliente, String dnicliente,
                           String direccion, String telefono, Boolean activo, Integer idcliente);

    @Query("SELECT COUNT(*) FROM Cliente")
    int obtenerCantidadCliente();
}
