package pe.tecnostore.tecnostore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.tecnostore.tecnostore.model.bd.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

}
