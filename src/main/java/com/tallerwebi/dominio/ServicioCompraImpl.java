package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.CompraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ServicioCompraImpl implements ServicioCompra {

    @Autowired
    private RepositorioCompra repositorioCompra;

    @Autowired
    private RepositorioCotizacion repositorioCotizacion;

    @Autowired
    public ServicioCompraImpl(RepositorioCompra repositorioCompra, RepositorioCotizacion repositorioCotizacion) {
        this.repositorioCompra = repositorioCompra;
        this.repositorioCotizacion = repositorioCotizacion;
    }
    @Override
    public CompraDTO guardarCompra(CompraDTO compraDTO) {
        Compra compra=new Compra();

        compra.setCantidadDeDivisasCompradas(compraDTO.getCompra().getCantidadDeDivisasCompradas());
        compra.setCotizacion(compraDTO.getCotizacion());
        compra.setPrecioPagado(compraDTO.getCompra().getPrecioPagado());

        repositorioCompra.guardarCompra(compra);

        CompraDTO compraDTO=new CompraDTO();
        compraDTO.setCotizacion();

        return compra;
    }

    @Override
    @Transactional
    public Double obtenerCotizacion(CompraDTO compraFormularioDTO) {
        return repositorioCotizacion.obtenerCotizacion(compraFormularioDTO.getCotizacion().getTipoMoneda());
    }
}
