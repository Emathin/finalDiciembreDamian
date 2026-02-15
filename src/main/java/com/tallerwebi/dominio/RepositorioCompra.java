package com.tallerwebi.dominio;

import org.hibernate.Session;

import java.util.List;

public interface RepositorioCompra {
    Compra guardarCompra(Compra compra);

    List<Compra> obtenerTodasLasCompras();
}
