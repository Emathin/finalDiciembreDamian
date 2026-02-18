package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Compra;
import com.tallerwebi.dominio.Cotizacion;
import com.tallerwebi.dominio.ServicioCompra;
import com.tallerwebi.dominio.TipoMoneda;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
public class ControladorCompraTest {

    @Mock
    private ServicioCompra servicioCompra;

    @InjectMocks
    private ControladorCompra controladorCompra;

    @Test
    public void queSePuedaGuardarUnaCompra() {

        //Preparacion
        CompraDTO compraFormulario = new CompraDTO();

        //Ejecucion
        controladorCompra.guardarCompra(compraFormulario);

        //Contrastacion
        verify(servicioCompra, times(1)).guardarCompra(compraFormulario);
    }

    @Test
    public void queSePuedaConsultarPesosRequeridosEnBaseALaCotizacionAntesDeComprar() {

        //Preparacion
        Cotizacion cotizacion = new Cotizacion(TipoMoneda.DOLAR, 200.00);
        Double cantidadPesosRequeridos = 200000.00;
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setCantidadDeDivisasCompradas(1000.00);
        compraDTO.setTipoMoneda(cotizacion.getTipoMoneda());
        when(servicioCompra.obtenerCotizacion(compraDTO)).thenReturn(cantidadPesosRequeridos);

        //Ejecucion
        ModelAndView mav = controladorCompra.calcularPesosRequeridos(compraDTO);

        //Contrastacion
        assertEquals("comprar", mav.getViewName());
        assertEquals(cantidadPesosRequeridos, mav.getModel().get("pesosEstimados"));
    }

    @Test
    public void queSePuedaConsultarLaCotizacionDeUnaMoneda() {

        //Preparacion
        CompraDTO compraDTO = new CompraDTO();

        compraDTO.setCotizacion(300.00);
        compraDTO.setTipoMoneda(TipoMoneda.DOLAR);
        compraDTO.setCantidadDeDivisasCompradas(1000.00);
        compraDTO.setFechaCompra(LocalDateTime.now());

        when(servicioCompra.obtenerCotizacion(compraDTO)).thenReturn(300.00);

        //Ejecucion
        ModelAndView mav = controladorCompra.consultar(compraDTO);

        //Contrastacion
        assertEquals("comprar", mav.getViewName());
        assertEquals(300.00, ((CompraDTO) mav.getModel().get("compraDTO")).getCotizacion());
    }

    @Test
    public void queSePuedanObtenerTodasLasCompras() {

        //Preparacion
        CompraDTO compraDTO1 = new CompraDTO();
        CompraDTO compraDTO2 = new CompraDTO();

        List<CompraDTO> compraDTOList = new ArrayList<>();
        compraDTOList.add(compraDTO1);
        compraDTOList.add(compraDTO2);

        when(servicioCompra.obtenerTodasLasCompras()).thenReturn(compraDTOList);

        //Ejecucion
        ModelAndView mav = controladorCompra.obtenerTodasLasCompras();

        //Contrastacion
        assertEquals("listadoDeCompras", mav.getViewName());
        assertEquals(2, ((java.util.List<CompraDTO>) mav.getModel().get("compras")).size());
        assertEquals(compraDTO1, ((java.util.List<CompraDTO>) mav.getModel().get("compras")).get(0));
        assertEquals(compraDTO2, ((java.util.List<CompraDTO>) mav.getModel().get("compras")).get(1));
    }


}
