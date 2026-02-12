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
    @Transactional
    public CompraDTO guardarCompra(CompraDTO compraDTO) {

        Cotizacion cotizacion=repositorioCotizacion.obtenerCotizacionCompleta(compraDTO.getCotizacion().getTipoMoneda());

        Compra compra=new Compra();

        compra.setCantidadDeDivisasCompradas(compraDTO.getCompra().getCantidadDeDivisasCompradas());
        compra.setCotizacion(cotizacion);
        compra.setPrecioPagado(compraDTO.getCompra().getPrecioPagado());

        repositorioCompra.guardarCompra(compra);

        compraDTO.setCompra(compra);
        compraDTO.getCompra().setPrecioPagado(compra.getPrecioPagado());
        compraDTO.setCotizacion(compra.getCotizacion());

        return compraDTO;
    }

    @Override
    @Transactional
    public Double obtenerCotizacion(CompraDTO compraFormularioDTO) {
        return repositorioCotizacion.obtenerCotizacion(compraFormularioDTO.getCotizacion().getTipoMoneda());
    }
}
