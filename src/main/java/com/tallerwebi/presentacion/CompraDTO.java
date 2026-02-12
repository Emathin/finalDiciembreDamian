package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Compra;
import com.tallerwebi.dominio.Cotizacion;

public class CompraDTO {
    private Compra compra;
    private Cotizacion cotizacion;

    public CompraDTO() {
        this.compra = new Compra();
        this.cotizacion = new Cotizacion();
    }
    public CompraDTO(Compra compra, Cotizacion cotizacion) {
        this.compra = compra;
        this.cotizacion = cotizacion;
    }

    public Compra getCompra() {
        return compra;
    }
    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }
    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }


}
