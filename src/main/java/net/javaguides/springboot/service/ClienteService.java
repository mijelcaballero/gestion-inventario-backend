package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Cliente;
import net.javaguides.springboot.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente createCliente(Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente getClienteByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente updateCliente(Long id, Cliente cliente) {
        Cliente existingCliente = clienteRepository.findById(id);
        if (existingCliente != null) {
            existingCliente.setNombre(cliente.getNombre());
            existingCliente.setEmail(cliente.getEmail());
            existingCliente.setDireccion(cliente.getDireccion());
            existingCliente.setTelefono(cliente.getTelefono());
            existingCliente.setPassword(cliente.getPassword());
            clienteRepository.update(existingCliente);
        }
        return existingCliente;
    }

    public void deleteCliente(Long id) {
        clienteRepository.delete(id);
    }
}
