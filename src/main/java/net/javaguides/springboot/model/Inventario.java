package net.javaguides.springboot.model;

import java.sql.Timestamp;

public class Inventario {

    private Long id;
    private Long productoId;
    private int cantidad;
    private Timestamp fechaActualizacion;

    public Inventario() {}

    public Inventario(Long productoId, int cantidad) {
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.fechaActualizacion = new Timestamp(System.currentTimeMillis());
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Timestamp fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
