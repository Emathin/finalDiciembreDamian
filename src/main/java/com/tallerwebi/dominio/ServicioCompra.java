package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.CompraDTO;

public interface ServicioCompra {
    CompraDTO guardarCompra(CompraDTO compraDTO);

    Double obtenerCotizacion(CompraDTO compraFormularioDTO);
}
