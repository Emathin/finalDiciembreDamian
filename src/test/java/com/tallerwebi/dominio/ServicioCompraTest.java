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

        Compra compraModelo = new Compra();

        when(repositorioCompra.guardarCompra(compraModelo)).thenReturn(compraModelo);

        servicioCompra.guardarCompra(compraModelo);

        verify(repositorioCompra,times(1)).guardarCompra(compraModelo);
    }

    @Test
    public void queSePuedaCalcularLaCotizacionDeUnaCompra(){

        Cotizacion cotizacionModelo = new Cotizacion(TipoMoneda.DOLAR,200.00);
        Compra compraModelo=new Compra();
        CompraDTO compraDTO=new CompraDTO();
        compraDTO.setCompra(compraModelo);
        compraDTO.setCotizacion(cotizacionModelo);

        compraModelo.setCotizacion(cotizacionModelo);
        compraModelo.setCantidad(1000.00);

        when(repositorioCotizacion.obtenerCotizacion(TipoMoneda.DOLAR)).thenReturn(200.00);

        Double cotizacion=servicioCompra.calcularCotizacion(compraDTO);

        verify(repositorioCotizacion,times(1)).obtenerCotizacion(TipoMoneda.DOLAR);
        assertEquals(200000.00,cotizacion);
    }


}
