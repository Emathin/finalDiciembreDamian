package com.tallerwebi.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioCompraImpl implements ServicioCompra {

    @Autowired
    private RepositorioCompra repositorioCompra;

    @Autowired
    public ServicioCompraImpl(RepositorioCompra repositorioCompra) {
        this.repositorioCompra = repositorioCompra;
    }

    @Override
    public Compra guardarCompra(Compra compra) {
        repositorioCompra.guardarCompra(compra);
        return compra;
    }
}
