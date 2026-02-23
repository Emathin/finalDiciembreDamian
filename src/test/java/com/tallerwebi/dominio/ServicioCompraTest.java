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
        // Preparación
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setTipoMoneda(TipoMoneda.DOLAR); // Asegúrate de que no sea null si el repo lo usa

        // Comportamiento (Mock)
        // No hace falta guardar el retorno en una variable 'compra' si no la vas a usar
        when(repositorioCompra.guardarCompra(any(Compra.class))).thenReturn(new Compra());
        when(repositorioCotizacion.obtenerCotizacionCompleta(any())).thenReturn(new Cotizacion());

        // Ejecución
        servicioCompra.guardarCompra(compraDTO);

        // Verificación: Usa 'any' también aquí
        verify(repositorioCompra, times(1)).guardarCompra(any(Compra.class));
    }

    @Test
    public void queSePuedaCalcularLaPesosRequeridosParaUnaCompraObteniendoLaCotizacion(){

        Cotizacion cotizacionModelo = new Cotizacion(TipoMoneda.DOLAR,200.00);
        CompraDTO compraDTO=new CompraDTO();
        compraDTO.setCotizacion(cotizacionModelo.getValor());
        compraDTO.setTipoMoneda(cotizacionModelo.getTipoMoneda());
        compraDTO.setCantidadDeDivisasCompradas(1000.00);

        when(repositorioCotizacion.obtenerCotizacion(compraDTO.getTipoMoneda())).thenReturn(200.00);

        compraDTO.setPrecioPagado(servicioCompra.obtenerCotizacion(compraDTO)*compraDTO.getCantidadDeDivisasCompradas());

        verify(repositorioCotizacion,times(1)).obtenerCotizacion(TipoMoneda.DOLAR);
        assertEquals(200000.00, compraDTO.getPrecioPagado());
    }

    @Test
    public void queSePuedanObtenerTodasLasCompras(){
        //Preparacion
           Compra compra1=new Compra();
           Compra compra2=new Compra();

           Cotizacion cotizacion1=new Cotizacion(TipoMoneda.DOLAR,200.00);
           Cotizacion cotizacion2=new Cotizacion(TipoMoneda.EURO,300.00);

           compra1.setCotizacion(cotizacion1);
           compra2.setCotizacion(cotizacion2);

           List<Compra> compras=new ArrayList<>();

           compras.add(compra1);
           compras.add(compra2);

           when(repositorioCompra.obtenerTodasLasCompras()).thenReturn(compras);

        //Ejecucion

        List<CompraDTO> comprasObtenidas=servicioCompra.obtenerTodasLasCompras();

        //Contrastacion
        verify(repositorioCompra,times(1)).obtenerTodasLasCompras();
        assertEquals(2,comprasObtenidas.size());
        assertEquals(compra1.getCotizacion().getTipoMoneda(),comprasObtenidas.get(0).getTipoMoneda());
        assertEquals(compra2.getCotizacion().getTipoMoneda(),comprasObtenidas.get(1).getTipoMoneda());
        assertEquals(compra1.getCotizacion().getValor(),comprasObtenidas.get(0).getCotizacion());
        assertEquals(compra2.getCotizacion().getValor(),comprasObtenidas.get(1).getCotizacion());
    }
}