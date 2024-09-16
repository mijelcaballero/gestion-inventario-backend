package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.EncabezadoFactura;
import net.javaguides.springboot.model.DetalleFactura;
import net.javaguides.springboot.service.FacturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @PostMapping("/facturas")
    public ResponseEntity<EncabezadoFactura> crearFactura(@RequestBody FacturaRequest facturaRequest) {
        EncabezadoFactura encabezado = facturaRequest.getEncabezado();
        List<DetalleFactura> detalles = facturaRequest.getDetalles();
        EncabezadoFactura nuevaFactura = facturaService.crearFactura(encabezado, detalles);
        return ResponseEntity.ok(nuevaFactura);
    }

    @GetMapping("/facturas")
    public ResponseEntity<List<EncabezadoFactura>> obtenerFacturas() {
        List<EncabezadoFactura> facturas = facturaService.obtenerFacturas();
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/facturas/{id}")
    public ResponseEntity<EncabezadoFactura> obtenerFacturaPorId(@PathVariable Long id) {
        EncabezadoFactura factura = facturaService.obtenerFacturaPorId(id);
        if (factura != null) {
            return ResponseEntity.ok(factura);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
