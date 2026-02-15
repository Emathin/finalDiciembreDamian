package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.CompraDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

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
    public void queSePuedaCalcularLaPesosRequeridosParaUnaCompra(){

        Cotizacion cotizacionModelo = new Cotizacion(TipoMoneda.DOLAR,200.00);
        CompraDTO compraDTO=new CompraDTO();
        compraDTO.setCotizacion(cotizacionModelo.getValor());
        compraDTO.setTipoMoneda(cotizacionModelo.getTipoMoneda());
        compraDTO.setCantidadDeDivisasCompradas(1000.00);

        when(repositorioCotizacion.obtenerCotizacion(compraDTO.getTipoMoneda())).thenReturn(200.00);

        compraDTO.setPrecioPagado(servicioCompra.obtenerCotizacion(compraDTO));

        verify(repositorioCotizacion,times(1)).obtenerCotizacion(TipoMoneda.DOLAR);
        assertEquals(200000.00, compraDTO.getPrecioPagado());
    }

    @Test
    public void queSePuedanObtenerTodasLasCompras(){
        //Preparacion
           Compra compra1=new Compra();
           Compra compra2=new Compra();
           List<Compra> compras=new ArrayList<>();
           compras.add(compra1);
           compras.add(compra2);
           when(repositorioCompra.obtenerTodasLasCompras()).thenReturn(compras);

        //Ejecucion

        List<Compra> comprasObtenidas=servicioCompra.obtenerTodasLasCompras();

        //Contrastacion
        verify(repositorioCompra,times(1)).obtenerTodasLasCompras();
        assertEquals(2,comprasObtenidas.size());
        assertEquals(compra1,comprasObtenidas.get(0));
        assertEquals(compra2,comprasObtenidas.get(1));
    }

}
