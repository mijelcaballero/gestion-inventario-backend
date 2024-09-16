package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.DetalleFactura;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DetalleFacturaRepository {

    private final JdbcTemplate jdbcTemplate;

    public DetalleFacturaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<DetalleFactura> rowMapper = new RowMapper<DetalleFactura>() {
        @Override
        public DetalleFactura mapRow(ResultSet rs, int rowNum) throws SQLException {
            DetalleFactura detalle = new DetalleFactura();
            detalle.setId(rs.getLong("id"));
            detalle.setEncabezadoFacturaId(rs.getLong("encabezado_factura_id"));
            detalle.setProductoId(rs.getLong("producto_id"));
            detalle.setServicioId(rs.getLong("servicio_id"));
            detalle.setCantidad(rs.getInt("cantidad"));
            detalle.setPrecioUnitario(rs.getDouble("precio_unitario"));
            detalle.setSubtotal(rs.getDouble("subtotal"));
            return detalle;
        }
    };

    public List<DetalleFactura> findAll() {
        String sql = "SELECT * FROM detalles_factura";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @SuppressWarnings("deprecation")
	public DetalleFactura findById(Long id) {
        String sql = "SELECT * FROM detalles_factura WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
    }

    public void save(DetalleFactura detalleFactura) {
        String sql = "INSERT INTO detalles_factura (encabezado_factura_id, producto_id, servicio_id, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, detalleFactura.getEncabezadoFacturaId(), detalleFactura.getProductoId(), detalleFactura.getServicioId(), detalleFactura.getCantidad(), detalleFactura.getPrecioUnitario(), detalleFactura.getSubtotal());
    }

    public void update(DetalleFactura detalleFactura) {
        String sql = "UPDATE detalles_factura SET encabezado_factura_id = ?, producto_id = ?, servicio_id = ?, cantidad = ?, precio_unitario = ?, subtotal = ? WHERE id = ?";
        jdbcTemplate.update(sql, detalleFactura.getEncabezadoFacturaId(), detalleFactura.getProductoId(), detalleFactura.getServicioId(), detalleFactura.getCantidad(), detalleFactura.getPrecioUnitario(), detalleFactura.getSubtotal(), detalleFactura.getId());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM detalles_factura WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
