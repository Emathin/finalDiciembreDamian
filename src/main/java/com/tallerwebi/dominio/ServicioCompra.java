package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.CompraDTO;

import java.util.List;

public interface ServicioCompra {
    void guardarCompra(CompraDTO compraDTO);

    Double obtenerCotizacion(CompraDTO compraFormularioDTO);

    List<CompraDTO> obtenerTodasLasCompras();

    List<CompraDTO> obtenerComprasPorMoneda(TipoMoneda tipoMoneda);
}