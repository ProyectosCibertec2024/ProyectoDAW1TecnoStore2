package pe.tecnostore.tecnostore.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.tecnostore.tecnostore.model.bd.Rol;
import pe.tecnostore.tecnostore.repository.RolRepository;
import pe.tecnostore.tecnostore.service.interfaces.IRolService;

import java.util.List;

@AllArgsConstructor
@Service
public class RolService implements IRolService {

    private RolRepository rolRepository;

    @Override
    public int obtenerIdRol() {
        return rolRepository.obtenerIdRol();
    }

    @Override
    public List<Rol> listadoRols() {
        return rolRepository.findAll();
    }

    @Override
    public void guardarRol(Rol rol) {
        rolRepository.save(rol);
    }

    @Override
    public Rol buscarRol(int id) {
        return rolRepository.findById(id).orElse(null);
    }
}
