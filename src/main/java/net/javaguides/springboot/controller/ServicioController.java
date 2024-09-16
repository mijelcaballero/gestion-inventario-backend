package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Servicio;
import net.javaguides.springboot.service.ServicioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class ServicioController {
	
	@Autowired
    private ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    // Crear un nuevo servicio
    @PostMapping
    public ResponseEntity<Servicio> createServicio(@RequestBody Servicio servicio) {
        Servicio nuevoServicio = servicioService.createServicio(servicio);
        return new ResponseEntity<>(nuevoServicio, HttpStatus.CREATED);
    }

    // Obtener un servicio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getServicioById(@PathVariable Long id) {
        Servicio servicio = servicioService.getServicioById(id);
        return ResponseEntity.ok(servicio);
    }

    // Obtener todos los servicios
    @GetMapping("/servicios")
    public List<Servicio> getAllServicios() {
        return servicioService.getAllServicios();
    }

    // Actualizar un servicio
    @PutMapping("/{id}")
    public ResponseEntity<Servicio> updateServicio(@PathVariable Long id, @RequestBody Servicio servicio) {
        Servicio servicioActualizado = servicioService.updateServicio(id, servicio);
        return ResponseEntity.ok(servicioActualizado);
    }

    // Eliminar un servicio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicio(@PathVariable Long id) {
        servicioService.deleteServicio(id);
        return ResponseEntity.noContent().build();
    }
}
