package com.tallerwebi.dominio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServicioCompraTest {

    @Mock
    private RepositorioCompra repositorioCompra;

    @InjectMocks
    private ServicioCompraImpl servicioCompra;

    @Test
    public void queSePuedaCrearUnaCompra(){

        Compra compraModelo = new Compra();

        when(repositorioCompra.guardarCompra(compraModelo)).thenReturn(compraModelo);

        servicioCompra.guardarCompra(compraModelo);

        verify(repositorioCompra,times(1)).guardarCompra(compraModelo);
    }


}
