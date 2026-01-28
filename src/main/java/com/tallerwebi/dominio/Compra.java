package com.tallerwebi.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Compra{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TipoMoneda tipoMoneda;
    private Double cantidad;
    private Double cotizacion;

    public Compra(TipoMoneda tipoMoneda, Double cantidad) {
        this.tipoMoneda = tipoMoneda;
        this.cantidad = cantidad;
    }

    public Compra() {

    }

    public Compra(long l, TipoMoneda tipoMoneda, double v) {
        this.id = l;
        this.tipoMoneda = tipoMoneda;
        this.cantidad = v;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoMoneda getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(TipoMoneda tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Double cotizacion) {
        this.cotizacion = cotizacion;
    }
}
