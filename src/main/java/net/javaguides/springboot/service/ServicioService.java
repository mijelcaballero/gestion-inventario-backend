package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Servicio;
import net.javaguides.springboot.repository.ServicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioService {
	
	
    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    public Servicio createServicio(Servicio servicio) {
        servicioRepository.save(servicio);
        return servicio;
    }

    public Servicio getServicioById(Long id) {
        return servicioRepository.findById(id);
    }

    public List<Servicio> getAllServicios() {
        return servicioRepository.findAll();
    }

    public Servicio updateServicio(Long id, Servicio servicio) {
        Servicio existingServicio = servicioRepository.findById(id);
        if (existingServicio != null) {
            existingServicio.setName(servicio.getName());
            existingServicio.setImage(servicio.getImage());
            existingServicio.setDescription(servicio.getDescription());
            existingServicio.setCategory(servicio.getCategory());
            existingServicio.setPrice(servicio.getPrice());
            servicioRepository.update(existingServicio);
        }
        return existingServicio;
    }

    public void deleteServicio(Long id) {
        servicioRepository.delete(id);
    }
}
