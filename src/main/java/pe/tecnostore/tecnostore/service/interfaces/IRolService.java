package pe.tecnostore.tecnostore.service.interfaces;

import pe.tecnostore.tecnostore.model.bd.Rol;

import java.util.List;

public interface IRolService {
    int obtenerIdRol();
    List<Rol> listadoRols();
    void guardarRol(Rol rol);
    Rol buscarRol(int id);
}
