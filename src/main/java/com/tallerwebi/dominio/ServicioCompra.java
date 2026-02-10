package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.CompraDTO;

public interface ServicioCompra {
    Compra guardarCompra(Compra compra);

    Double calcularCotizacion(CompraDTO compraFormulario);
}
