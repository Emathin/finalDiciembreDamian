package com.tallerwebi.dominio;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double cantidadDeDivisasCompradas;
    @ManyToOne
    @JoinColumn(name = "cotizacion_id_cotizacion")
    private Cotizacion cotizacion;
    private Double precioPagado;
    private LocalDateTime fechaCompra;

    public Compra() {

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCantidadDeDivisasCompradas() {
        return cantidadDeDivisasCompradas;
    }

    public void setCantidadDeDivisasCompradas(Double cantidad) {
        this.cantidadDeDivisasCompradas = cantidad;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Double getPrecioPagado() {
        return precioPagado;
    }
    public void setPrecioPagado(Double precioPagado) {
        this.precioPagado = precioPagado;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}
