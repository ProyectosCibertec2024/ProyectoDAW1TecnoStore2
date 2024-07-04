package pe.tecnostore.tecnostore.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.tecnostore.tecnostore.model.bd.Venta;
import pe.tecnostore.tecnostore.model.dto.object.dashboard.VentaDTO;
import pe.tecnostore.tecnostore.repository.VentaRepository;
import pe.tecnostore.tecnostore.service.interfaces.IVentasService;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class VentaService implements IVentasService {
    private VentaRepository ventaRepository;

    @Override
    public List<Venta> listadoVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public void guardarVentas(Venta venta) {
        String numeroVenta = generarNumeroVenta();
        venta.setNumeroventa(numeroVenta);
        ventaRepository.save(venta);
    }

    @Override
    public Venta buscarVenta(int id) {
        return ventaRepository.findById(id).orElse(null);
    }

    @Override
    public String generarNumeroVenta() {
        Venta ultimaVenta = ventaRepository.findTopByOrderByIdventaDesc();
        String ultimoNumero = ultimaVenta != null ? ultimaVenta.getNumeroventa() : "V00000";
        int numero = Integer.parseInt(ultimoNumero.substring(1)) + 1;
        return String.format("V%05d", numero);
    }

    @Override
    public int obtenerIdVenta() {
        return ventaRepository.obtenerIdVenta();
    }

    @Override
    public void actualizarventa(int idcliente, int idventa) {
        ventaRepository.guardarVenta(idcliente, idventa);
    }

    @Override
    public List<Venta> consultaVentaxUsuario(int idusuario) {
        return ventaRepository.consultaVentaxUsuario(idusuario);
    }

    @Override
    public double consultarVentaToIdVentaxTotal(int idventa) {
        return ventaRepository.consultarVentaToIdVentaxTotal(idventa);
    }

    @Override
    public List<VentaDTO> obtnerVentasxMes(LocalDate incio, LocalDate fin) {
        return ventaRepository.obtnerVentasxMes(incio, fin);
    }
}
