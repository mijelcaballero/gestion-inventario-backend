package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Producto;
import net.javaguides.springboot.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Crear Producto
    public Producto createProducto(Producto producto) {
        productoRepository.save(producto);
        return producto;
    }

    // Buscar producto por ID
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    // Listar todos los productos
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    // Actualizar Producto
    public Producto updateProducto(Long id, Producto producto) {
        Producto existingProducto = productoRepository.findById(id);
        if (existingProducto != null) {
            existingProducto.setName(producto.getName());
            existingProducto.setImage(producto.getImage());
            existingProducto.setDescription(producto.getDescription());
            existingProducto.setCategory(producto.getCategory());
            existingProducto.setPrice(producto.getPrice());
            productoRepository.update(existingProducto);
        }
        return existingProducto;
    }

    // Eliminar Producto
    public void deleteProducto(Long id) {
        productoRepository.delete(id);
    }
}
