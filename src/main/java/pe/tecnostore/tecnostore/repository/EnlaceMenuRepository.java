package pe.tecnostore.tecnostore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.tecnostore.tecnostore.model.bd.EnlaceMenu;

@Repository
public interface EnlaceMenuRepository extends JpaRepository<EnlaceMenu, Integer> {
    @Query("SELECT coalesce(max(idenlace), 0) + 1 FROM EnlaceMenu ")
    int obtenerIdEnlaceMenu();
}
