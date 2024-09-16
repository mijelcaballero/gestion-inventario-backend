package net.javaguides.springboot.model;



public class DetalleFactura {
	
    private Long id;
    private Long encabezadoFacturaId;
    private Long productoId;
    private Long servicioId;
    private int cantidad;
    private Double precioUnitario;
    private Double subtotal;

    public DetalleFactura() {}

    public DetalleFactura(Long encabezadoFacturaId, Long productoId, Long servicioId, int cantidad, Double precioUnitario) {
        this.encabezadoFacturaId = encabezadoFacturaId;
        this.productoId = productoId;
        this.servicioId = servicioId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = cantidad * precioUnitario;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEncabezadoFacturaId() {
        return encabezadoFacturaId;
    }

    public void setEncabezadoFacturaId(Long encabezadoFacturaId) {
        this.encabezadoFacturaId = encabezadoFacturaId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
