package net.javaguides.springboot.controller;

import net.javaguides.springboot.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    // Verificar stock disponible
    @GetMapping("/inventario/verificar/{productoId}/{cantidad}")
    public ResponseEntity<String> verificarStock(@PathVariable Long productoId, @PathVariable int cantidad) {
        boolean disponible = inventarioService.hayStock(productoId, cantidad);
        
        if (disponible) {
            return ResponseEntity.ok("Stock disponible");
        } else {
            return ResponseEntity.badRequest().body("Stock insuficiente");
        }
    }

    // Actualizar stock después de una venta
    @PostMapping("/inventario/vender/{productoId}/{cantidad}")
    public ResponseEntity<String> venderProducto(@PathVariable Long productoId, @PathVariable int cantidad) {
        boolean disponible = inventarioService.hayStock(productoId, cantidad);
        
        if (disponible) {
            inventarioService.actualizarStock(productoId, cantidad);
            return ResponseEntity.ok("Venta registrada y stock actualizado");
        } else {
            return new ResponseEntity<>("No hay suficiente stock", HttpStatus.BAD_REQUEST);
        }
    }

    // Agregar stock a un producto
    @PostMapping("/inventario/agregar/{productoId}/{cantidad}")
    public ResponseEntity<String> agregarStock(@PathVariable Long productoId, @PathVariable int cantidad) {
        inventarioService.agregarStock(productoId, cantidad);
        return new ResponseEntity<>("Stock actualizado correctamente", HttpStatus.OK);
    }
}
