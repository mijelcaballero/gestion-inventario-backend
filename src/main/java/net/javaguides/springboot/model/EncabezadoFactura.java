package net.javaguides.springboot.model;

import java.sql.Timestamp;

public class EncabezadoFactura {
	
    private Long id;
    private Long clienteId;
    private Timestamp fecha;
    private Double total;

    public EncabezadoFactura() {}

    public EncabezadoFactura(Long clienteId, Double total) {
        this.clienteId = clienteId;
        this.total = total;
        this.fecha = new Timestamp(System.currentTimeMillis());
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
