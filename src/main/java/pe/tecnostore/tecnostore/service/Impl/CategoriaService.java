package pe.tecnostore.tecnostore.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.tecnostore.tecnostore.model.bd.Categoria;
import pe.tecnostore.tecnostore.repository.CategoriaRepository;
import pe.tecnostore.tecnostore.service.interfaces.ICategoriaService;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoriaService implements ICategoriaService {

    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listadoCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public void guardarCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    public Categoria buscarCategoria(int id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarCategoria(int id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public int obtenerID() {
        return categoriaRepository.obtenerIdGenerado();
    }
}
