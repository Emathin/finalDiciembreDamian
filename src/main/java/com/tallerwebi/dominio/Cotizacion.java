package com.tallerwebi.dominio;

import javax.persistence.*;

@Entity
public class Cotizacion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idCotizacion;
    @Enumerated(EnumType.STRING)//Para que en DB se lea Dolar en vez de 0.
    private TipoMoneda tipoMoneda;
    private Double valor;

    public Cotizacion() {
    }
    public Cotizacion(TipoMoneda tipoMoneda, Double valor) {
        this.tipoMoneda = tipoMoneda;
        this.valor = valor;
    }
    public TipoMoneda getTipoMoneda() {
        return tipoMoneda;
    }

    public Double getValor() {
        return valor;
    }
}
