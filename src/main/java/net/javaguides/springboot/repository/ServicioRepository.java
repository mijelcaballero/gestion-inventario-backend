package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Servicio;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServicioRepository {

    private final JdbcTemplate jdbcTemplate;

    public ServicioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper para mapear el resultado de la consulta a la clase Servicio
    private RowMapper<Servicio> rowMapper = (rs, rowNum) -> new Servicio(
            rs.getString("name"),
            rs.getString("image"),
            rs.getString("descript"),
            rs.getString("category"),
            rs.getDouble("price")
    );

    // Crear un nuevo servicio
    public int save(Servicio servicio) {
        String sql = "INSERT INTO servicios (name, image, description, category, price) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, servicio.getName(), servicio.getImage(), servicio.getDescription(), servicio.getCategory(), servicio.getPrice());
    }

    // Buscar un servicio por ID
    public Servicio findById(Long id) {
        String sql = "SELECT * FROM servicios WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    // Obtener todos los servicios
    public List<Servicio> findAll() {
        String sql = "SELECT * FROM servicios";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Actualizar un servicio
    public int update(Servicio servicio) {
        String sql = "UPDATE servicios SET name = ?, image = ?, description = ?, category = ?, price = ? WHERE id = ?";
        return jdbcTemplate.update(sql, servicio.getName(), servicio.getImage(), servicio.getDescription(), servicio.getCategory(), servicio.getPrice(), servicio.getId());
    }

    // Eliminar un servicio
    public int delete(Long id) {
        String sql = "DELETE FROM servicios WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
