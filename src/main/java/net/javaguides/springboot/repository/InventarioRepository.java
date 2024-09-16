package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Inventario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InventarioRepository {

    private final JdbcTemplate jdbcTemplate;

    public InventarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Inventario> rowMapper = (rs, rowNum) -> new Inventario(
            rs.getLong("producto_id"),
            rs.getInt("cantidad")
    );

    // Obtener inventario por ID de producto
    public Inventario findByProductoId(Long productoId) {
        String sql = "SELECT * FROM inventarios WHERE producto_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, productoId);
    }

    // Actualizar inventario
    public int update(Inventario inventario) {
        String sql = "UPDATE inventarios SET cantidad = ?, fecha_actualizacion = ? WHERE producto_id = ?";
        return jdbcTemplate.update(sql, inventario.getCantidad(), inventario.getFechaActualizacion(), inventario.getProductoId());
    }
}
