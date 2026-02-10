package com.tallerwebi.dominio;

import javax.persistence.*;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double cantidad;
    @ManyToOne
    @JoinColumn(name = "cotizacion_id_cotizacion")
    private Cotizacion cotizacion;
    private Double precioPagado;

    public Compra() {

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
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
}
