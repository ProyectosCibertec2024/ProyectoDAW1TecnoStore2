package pe.tecnostore.tecnostore.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.tecnostore.tecnostore.model.bd.EnlaceMenu;
import pe.tecnostore.tecnostore.repository.EnlaceMenuRepository;
import pe.tecnostore.tecnostore.service.interfaces.IEnlaceMenuService;

import java.util.List;

@AllArgsConstructor
@Service
public class EnlaceMenuService implements IEnlaceMenuService {
    private EnlaceMenuRepository enlaceMenuRepository;

    @Override
    public List<EnlaceMenu> listadoEnlaceMenus() {
        return enlaceMenuRepository.findAll();
    }

    @Override
    public void guardarEnlaceMenus(EnlaceMenu enlaceMenu) {
        enlaceMenuRepository.save(enlaceMenu);
    }

    @Override
    public EnlaceMenu buscarEnlaceMenu(int id) {
        return enlaceMenuRepository.findById(id).orElse(null);
    }

    @Override
    public int obtenerIdEnlaceMenu() {
        return enlaceMenuRepository.obtenerIdEnlaceMenu();
    }
}
