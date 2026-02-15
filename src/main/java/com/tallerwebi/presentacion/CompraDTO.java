package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Compra;
import com.tallerwebi.dominio.Cotizacion;
import com.tallerwebi.dominio.TipoMoneda;

public class CompraDTO {

    private TipoMoneda tipoMoneda;
    private Double cotizacion;
    private Double precioPagado;
    private Double CantidadDeDivisasCompradas;

  public CompraDTO() {
    }

    public CompraDTO(TipoMoneda tipoMoneda, Double cotizacion, Double precioPagado, Double cantidadDeDivisasCompradas) {
        this.tipoMoneda = tipoMoneda;
        this.cotizacion = cotizacion;
        this.precioPagado = precioPagado;
        CantidadDeDivisasCompradas = cantidadDeDivisasCompradas;
    }

    public TipoMoneda getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(TipoMoneda tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public Double getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Double cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Double getPrecioPagado() {
        return precioPagado;
    }

    public void setPrecioPagado(Double precioPagado) {
        this.precioPagado = precioPagado;
    }

    public Double getCantidadDeDivisasCompradas() {
        return CantidadDeDivisasCompradas;
    }

    public void setCantidadDeDivisasCompradas(Double cantidadDeDivisasCompradas) {
        CantidadDeDivisasCompradas = cantidadDeDivisasCompradas;
    }
}
