package net.javaguides.springboot.service;


import net.javaguides.springboot.model.Inventario;
import net.javaguides.springboot.repository.InventarioRepository;
import org.springframework.stereotype.Service;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    // Verificar si hay suficiente stock antes de una venta
    public boolean hayStock(Long productoId, int cantidadSolicitada) {
        Inventario inventario = inventarioRepository.findByProductoId(productoId);
        return inventario.getCantidad() >= cantidadSolicitada;
    }

    // Actualizar el stock después de una venta
    public void actualizarStock(Long productoId, int cantidadVendida) {
        Inventario inventario = inventarioRepository.findByProductoId(productoId);
        int nuevaCantidad = inventario.getCantidad() - cantidadVendida;
        inventario.setCantidad(nuevaCantidad);
        inventario.setFechaActualizacion(new java.sql.Timestamp(System.currentTimeMillis()));
        inventarioRepository.update(inventario);
    }

    // Actualizar stock cuando se agrega nuevo producto
    public void agregarStock(Long productoId, int cantidadAgregada) {
        Inventario inventario = inventarioRepository.findByProductoId(productoId);
        int nuevaCantidad = inventario.getCantidad() + cantidadAgregada;
        inventario.setCantidad(nuevaCantidad);
        inventario.setFechaActualizacion(new java.sql.Timestamp(System.currentTimeMillis()));
        inventarioRepository.update(inventario);
    }
}
