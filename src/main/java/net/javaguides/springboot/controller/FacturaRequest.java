package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.EncabezadoFactura;
import net.javaguides.springboot.model.DetalleFactura;

import java.util.List;

public class FacturaRequest {

    private EncabezadoFactura encabezado;
    private List<DetalleFactura> detalles;
    
    
    public FacturaRequest() {
	}

	// Getters y Setters
    public EncabezadoFactura getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(EncabezadoFactura encabezado) {
        this.encabezado = encabezado;
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleFactura> detalles) {
        this.detalles = detalles;
    }
}
