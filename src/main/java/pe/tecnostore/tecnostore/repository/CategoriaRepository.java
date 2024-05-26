package pe.tecnostore.tecnostore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.tecnostore.tecnostore.model.bd.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Query(value = "SELECT coalesce(max(c.idcategoria), 0) + 1 FROM categoria c", nativeQuery = true)
    int obtenerIdGenerado();
}
