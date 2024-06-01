package pe.tecnostore.tecnostore.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.tecnostore.tecnostore.model.bd.RolEnlace;
import pe.tecnostore.tecnostore.model.bd.RolEnlacePK;
import pe.tecnostore.tecnostore.model.dto.object.RolMenuDTO;
import pe.tecnostore.tecnostore.model.dto.object.RolMenuIdRolDTO;
import pe.tecnostore.tecnostore.repository.RolEnlaceRepository;
import pe.tecnostore.tecnostore.service.interfaces.IRolEnlaceService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RolEnlaceService implements IRolEnlaceService {

    private RolEnlaceRepository rolEnlaceRepository;

    @Override
    public void asignarMenusARol(Integer idrol, List<Integer> idmenus) {
        for (Integer idmenu : idmenus) {
            RolEnlacePK pk = new RolEnlacePK();
            pk.setIdrol(idrol);
            pk.setIdenlace(idmenu);
            Optional<RolEnlace> existingRelation = rolEnlaceRepository.findByPkIdrolAndPkIdenlace(pk.getIdrol(), pk.getIdenlace());

            if (existingRelation.isEmpty()) {
                RolEnlace re = new RolEnlace();
                re.setPk(pk);
                rolEnlaceRepository.save(re);
            }
        }
    }

    @Override
    public List<RolMenuDTO> obtenerMenusRol() {
        return rolEnlaceRepository.obtenerMenusRol();
    }

    @Override
    public RolMenuIdRolDTO obtenerMenusRolxIdRol(int idrol) {
        return rolEnlaceRepository.obtenerMenusRolxIdRol(idrol);
    }

    @Override
    public void asignarMenusARolUpdate(Integer idrol, List<Integer> idmenus) {
        // Obtener todas las asociaciones de menú existentes para el rol
        List<RolEnlace> asociacionesExistentes = rolEnlaceRepository.findByPkIdrol(idrol);

        // Eliminar las asociaciones existentes que no están presentes en las nuevas asociaciones
        for (RolEnlace asociacionExistente : asociacionesExistentes) {
            if (!idmenus.contains(asociacionExistente.getPk().getIdenlace())) {
                rolEnlaceRepository.delete(asociacionExistente);
            }
        }

        // Agregar las nuevas asociaciones de menú que no están presentes actualmente en las asociaciones existentes
        for (Integer idmenu : idmenus) {
            boolean existe = asociacionesExistentes.stream()
                    .anyMatch(asociacion -> asociacion.getPk().getIdenlace() == (idmenu));
            if (!existe) {
                RolEnlacePK pk = new RolEnlacePK();
                pk.setIdrol(idrol);
                pk.setIdenlace(idmenu);
                RolEnlace nuevaAsociacion = new RolEnlace();
                nuevaAsociacion.setPk(pk);
                rolEnlaceRepository.save(nuevaAsociacion);
            }
        }
    }
}
