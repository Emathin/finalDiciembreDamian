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
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@Transactional
public class ControladorCompraTest {

    @Mock
    private ServicioCompra servicioCompra;

    @InjectMocks
    private ControladorCompra controladorCompra;

    @Test
    public void queSePuedaGuardarUnaCompraYMostrarla(){
        //Preparacion
        Compra compraFormulario=new Compra();
        Cotizacion cotizacionFormulario=new Cotizacion();
        CompraDTO compraEnBlancoDTO=new CompraDTO();
        CompraDTO compraPersistidaDTO=new CompraDTO();
        compraPersistidaDTO.setCotizacion(cotizacionFormulario);
        compraPersistidaDTO.setCompra(compraFormulario);

        when(servicioCompra.guardarCompra(compraEnBlancoDTO)).thenReturn(compraPersistidaDTO);

        //Ejecucion
        ModelAndView mav=controladorCompra.guardarCompra(compraEnBlancoDTO);

        //Contrastacion
        assertEquals(compraPersistidaDTO,mav.getModel().get("compra"));
        assertEquals("compraGuardada",mav.getViewName());
    }

    @Test

    public void queSePuedaConsultarPesosRequeridosEnBaseALaCotizacionAntesDeComprar(){
        //Preparacion
            Cotizacion cotizacion=new Cotizacion(TipoMoneda.DOLAR,200.00);

            Compra compraFormulario=new Compra();

            compraFormulario.setCotizacion(cotizacion);

            Double cantidadDolaresAComprar=1000.00;

            compraFormulario.setCantidadDeDivisasCompradas(cantidadDolaresAComprar);

            Double cantidadPesosRequeridos=200000.00;

            CompraDTO compraDTO=new CompraDTO();

            compraDTO.setCompra(compraFormulario);

            compraDTO.setCotizacion(cotizacion);

            when(servicioCompra.obtenerCotizacion(compraDTO)).thenReturn(cantidadPesosRequeridos);

        //Ejecucion
        ModelAndView mav=controladorCompra.calcularPesosRequeridos(compraDTO);

        //Contrastacion
        assertEquals(mav.getViewName(),"comprar");
        assertEquals(cantidadPesosRequeridos,mav.getModel().get("pesosEstimados"));
    }
}
