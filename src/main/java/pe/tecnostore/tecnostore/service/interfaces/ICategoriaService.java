package pe.tecnostore.tecnostore.service.interfaces;

import pe.tecnostore.tecnostore.model.bd.Categoria;

import java.util.List;

public interface ICategoriaService {
    List<Categoria> listadoCategorias();
    void guardarCategoria(Categoria categoria);
    Categoria buscarCategoria(int id);
    void eliminarCategoria(int id);
    int obtenerID();
}
