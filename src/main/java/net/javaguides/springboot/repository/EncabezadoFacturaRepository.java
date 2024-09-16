package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.EncabezadoFactura;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EncabezadoFacturaRepository {

    private final JdbcTemplate jdbcTemplate;

    public EncabezadoFacturaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<EncabezadoFactura> rowMapper = new RowMapper<EncabezadoFactura>() {
        @Override
        public EncabezadoFactura mapRow(ResultSet rs, int rowNum) throws SQLException {
            EncabezadoFactura encabezado = new EncabezadoFactura();
            encabezado.setId(rs.getLong("id"));
            encabezado.setClienteId(rs.getLong("cliente_id"));
            encabezado.setFecha(rs.getTimestamp("fecha"));
            encabezado.setTotal(rs.getDouble("total"));
            return encabezado;
        }
    };

    public List<EncabezadoFactura> findAll() {
        String sql = "SELECT * FROM encabezados_factura";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @SuppressWarnings("deprecation")
	public EncabezadoFactura findById(Long id) {
        String sql = "SELECT * FROM encabezados_factura WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
    }

    public void save(EncabezadoFactura encabezadoFactura) {
        String sql = "INSERT INTO encabezados_factura (cliente_id, fecha, total) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, encabezadoFactura.getClienteId(), encabezadoFactura.getFecha(), encabezadoFactura.getTotal());
    }

    public void update(EncabezadoFactura encabezadoFactura) {
        String sql = "UPDATE encabezados_factura SET cliente_id = ?, fecha = ?, total = ? WHERE id = ?";
        jdbcTemplate.update(sql, encabezadoFactura.getClienteId(), encabezadoFactura.getFecha(), encabezadoFactura.getTotal(), encabezadoFactura.getId());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM encabezados_factura WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
