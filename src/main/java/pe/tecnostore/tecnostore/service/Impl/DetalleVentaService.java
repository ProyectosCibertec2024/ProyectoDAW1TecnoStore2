package pe.tecnostore.tecnostore.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.tecnostore.tecnostore.model.bd.DetalleVenta;
import pe.tecnostore.tecnostore.model.bd.Venta;
import pe.tecnostore.tecnostore.repository.DetalleVentaRepository;
import pe.tecnostore.tecnostore.repository.VentaRepository;
import pe.tecnostore.tecnostore.service.interfaces.IDetalleVentaService;

import java.util.List;

@AllArgsConstructor
@Service
public class DetalleVentaService implements IDetalleVentaService {

    private DetalleVentaRepository detalleVentaRepository;
    private VentaRepository ventaRepository;

    @Override
    public int obtenerIdDetalleVenta() {
        return detalleVentaRepository.obtenerIdDetalleVenta();
    }

    @Override
    public List<DetalleVenta> obtenerIdVentaToDetalleVenta(int idventa) {
        return detalleVentaRepository.obtenerIdVentaToDetalleVenta(idventa);
    }

    @Override
    public void guardarDetalleVenta(DetalleVenta detalleVenta) {
        detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public void actualizarVentaTotal(Integer idventa) {
        Double total = detalleVentaRepository.totalVenta(idventa);
        if(total == null) {
            total = 0.0;
        }
        ventaRepository.actualizarTotalVenta(total, idventa);
    }

    @Override
    public DetalleVenta buscarDetalleVenta(int id) {
        return detalleVentaRepository.findById(id).orElse(null);
    }
}
