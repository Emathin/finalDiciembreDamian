package com.tallerwebi.dominio;

import org.hibernate.Session;

public interface RepositorioCotizacion {

    Double obtenerCotizacion(TipoMoneda tipoMoneda);

    void guardarCotizacion(Cotizacion cotizacion1);
}
