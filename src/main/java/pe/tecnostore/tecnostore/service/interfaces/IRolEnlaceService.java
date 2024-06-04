package pe.tecnostore.tecnostore.service.interfaces;

import pe.tecnostore.tecnostore.model.dto.object.gestion.usuarios.RolMenuDTO;
import pe.tecnostore.tecnostore.model.dto.object.gestion.usuarios.RolMenuIdRolDTO;

import java.util.List;

public interface IRolEnlaceService {
    void asignarMenusARol(Integer idrol, List<Integer> idmenu);
    List<RolMenuDTO> obtenerMenusRol();
    RolMenuIdRolDTO obtenerMenusRolxIdRol(int idrol);
    void asignarMenusARolUpdate(Integer idrol, List<Integer> idmenus);
}
