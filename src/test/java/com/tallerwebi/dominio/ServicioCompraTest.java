package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.CompraDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServicioCompraTest {

    @Mock
    private RepositorioCompra repositorioCompra;

    @Mock
    private RepositorioCotizacion repositorioCotizacion;

    @InjectMocks
    private ServicioCompraImpl servicioCompra;

    @Test
    public void queSePuedaCrearUnaCompra(){

        CompraDTO compraDTO = new CompraDTO();
        Compra compra=new Compra();

        when(repositorioCompra.guardarCompra(compra)).thenReturn(compra);

        servicioCompra.guardarCompra(compraDTO);

        verify(repositorioCompra,times(1)).guardarCompra(compra);
    }

    @Test
    public void queSePuedaCalcularLaCotizacionDeUnaCompra(){

        Cotizacion cotizacionModelo = new Cotizacion(TipoMoneda.DOLAR,200.00);
        Compra compraModelo=new Compra();
        CompraDTO compraDTO=new CompraDTO();
        compraDTO.setCompra(compraModelo);
        compraDTO.setCotizacion(cotizacionModelo);

        compraModelo.setCotizacion(cotizacionModelo);
        compraModelo.setCantidadDeDivisasCompradas(1000.00);

        when(repositorioCotizacion.obtenerCotizacion(TipoMoneda.DOLAR)).thenReturn(200.00);

        Double cotizacion=servicioCompra.obtenerCotizacion(compraDTO);

        verify(repositorioCotizacion,times(1)).obtenerCotizacion(TipoMoneda.DOLAR);
        assertEquals(200000.00,cotizacion);
    }


}
