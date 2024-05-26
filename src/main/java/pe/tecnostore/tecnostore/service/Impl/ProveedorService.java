package pe.tecnostore.tecnostore.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.tecnostore.tecnostore.model.bd.Proveedor;
import pe.tecnostore.tecnostore.repository.ProveedorRepository;
import pe.tecnostore.tecnostore.service.interfaces.IProveedorService;

import java.util.List;

@AllArgsConstructor
@Service
public class ProveedorService implements IProveedorService {

    private ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> listaProveedores() {
        return proveedorRepository.findAll();
    }

    @Override
    public void guardarProveedor(Proveedor proveedor) {
        proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor buscarProveedor(int id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    @Override
    public int obtenerIdProveedor() {
        return proveedorRepository.obtenerIdProveedor();
    }

    @Override
    public void actualizarProveedor(String nomproveedor, String telefono, String correo, Boolean estado, Integer idempresa, Integer idproveedor) {
        proveedorRepository.actualizarProveedor(nomproveedor,telefono,correo,estado,idempresa,idproveedor);
    }
}
