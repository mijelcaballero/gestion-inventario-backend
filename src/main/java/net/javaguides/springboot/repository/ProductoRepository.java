package net.javaguides.springboot.repository;


import net.javaguides.springboot.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Crear producto
    public void save(Producto producto) {
        String sql = "INSERT INTO productos (name, image, description, category, price) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, producto.getName(), producto.getImage(), producto.getDescription(), producto.getCategory(), producto.getPrice());
    }

    // Buscar producto por ID
    public Producto findById(Long id) {
        String sql = "SELECT * FROM productos WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductoRowMapper());
    }

    // Listar todos los productos
    public List<Producto> findAll() {
        String sql = "SELECT * FROM productos";
        return jdbcTemplate.query(sql, new ProductoRowMapper());
    }

    // Actualizar producto
    public void update(Producto producto) {
        String sql = "UPDATE productos SET name = ?, image = ?, description = ?, category = ?, price = ? WHERE id = ?";
        jdbcTemplate.update(sql, producto.getName(), producto.getImage(), producto.getDescription(), producto.getCategory(), producto.getPrice(), producto.getId());
    }

    // Eliminar producto
    public void delete(Long id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // RowMapper para mapear el resultado de la consulta
    public static class ProductoRowMapper implements RowMapper<Producto> {
        @Override
        public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
            Producto producto = new Producto();
            producto.setId(rs.getLong("id"));
            producto.setName(rs.getString("name"));
            producto.setImage(rs.getString("image"));
            producto.setDescription(rs.getString("descript"));
            producto.setCategory(rs.getString("category"));
            producto.setPrice(rs.getDouble("price"));
            return producto;
        }
    }
}

