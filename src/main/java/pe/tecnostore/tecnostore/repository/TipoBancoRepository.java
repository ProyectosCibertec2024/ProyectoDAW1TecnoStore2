package pe.tecnostore.tecnostore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.tecnostore.tecnostore.model.bd.TipoBanco;

@Repository
public interface TipoBancoRepository extends JpaRepository<TipoBanco, Integer> {
}
