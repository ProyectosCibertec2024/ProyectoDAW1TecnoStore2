package pe.tecnostore.tecnostore.service.interfaces;

import pe.tecnostore.tecnostore.model.bd.EnlaceMenu;

import java.util.List;

public interface IEnlaceMenuService {
    List<EnlaceMenu> listadoEnlaceMenus();
    void guardarEnlaceMenus(EnlaceMenu enlaceMenu);
    EnlaceMenu buscarEnlaceMenu(int id);
    int obtenerIdEnlaceMenu();
}
