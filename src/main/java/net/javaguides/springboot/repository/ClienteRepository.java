package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Cliente;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClienteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper para mapear el resultado de la consulta a la clase Cliente
    private RowMapper<Cliente> rowMapper = (rs, rowNum) -> new Cliente(
            rs.getString("nombre"),
            rs.getString("email"),
            rs.getString("direccion"),
            rs.getString("telefono"),
            rs.getString("password")
    );

    // Crear un nuevo cliente
    public int save(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, email, direccion, telefono, password) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, cliente.getNombre(), cliente.getEmail(), cliente.getDireccion(), cliente.getTelefono(), cliente.getPassword());
    }

    // Buscar un cliente por ID
    public Cliente findById(Long id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    // Buscar un cliente por email
    public Cliente findByEmail(String email) {
        String sql = "SELECT * FROM clientes WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, email);
    }

    // Obtener todos los clientes
    public List<Cliente> findAll() {
        String sql = "SELECT * FROM clientes";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Actualizar un cliente
    public int update(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre = ?, email = ?, direccion = ?, telefono = ?, password = ? WHERE id = ?";
        return jdbcTemplate.update(sql, cliente.getNombre(), cliente.getEmail(), cliente.getDireccion(), cliente.getTelefono(), cliente.getPassword(), cliente.getId());
    }

    // Eliminar un cliente
    public int delete(Long id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
