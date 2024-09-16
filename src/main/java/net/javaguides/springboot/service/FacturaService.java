package net.javaguides.springboot.service;

import net.javaguides.springboot.model.EncabezadoFactura;
import net.javaguides.springboot.model.DetalleFactura;
import net.javaguides.springboot.repository.EncabezadoFacturaRepository;
import net.javaguides.springboot.repository.DetalleFacturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaService {

    private final EncabezadoFacturaRepository encabezadoFacturaRepository;
    private final DetalleFacturaRepository detalleFacturaRepository;

    public FacturaService(EncabezadoFacturaRepository encabezadoFacturaRepository, DetalleFacturaRepository detalleFacturaRepository) {
        this.encabezadoFacturaRepository = encabezadoFacturaRepository;
        this.detalleFacturaRepository = detalleFacturaRepository;
    }

    public EncabezadoFactura crearFactura(EncabezadoFactura encabezado, List<DetalleFactura> detalles) {
        // Guardar encabezado
        encabezadoFacturaRepository.save(encabezado);

        // Guardar detalles
        for (DetalleFactura detalle : detalles) {
            detalle.setEncabezadoFacturaId(encabezado.getId());
            detalleFacturaRepository.save(detalle);
        }

        return encabezado;
    }

    public List<EncabezadoFactura> obtenerFacturas() {
        return encabezadoFacturaRepository.findAll();
    }

    public EncabezadoFactura obtenerFacturaPorId(Long id) {
        return encabezadoFacturaRepository.findById(id);
    }
}
