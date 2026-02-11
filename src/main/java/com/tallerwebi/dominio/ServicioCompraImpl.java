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
    public Compra guardarCompra(Compra compra) {
        repositorioCompra.guardarCompra(compra);
        return compra;
    }

    @Override
    @Transactional
    public Double calcularCotizacion(CompraDTO compraFormulario) {
        return repositorioCotizacion.obtenerCotizacion(compraFormulario.getCotizacion().getTipoMoneda())
        *compraFormulario.getCompra().getCantidad();
    }
}
