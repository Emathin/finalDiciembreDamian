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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ControladorCompraTest {

    @Mock
    private ServicioCompra servicioCompra;

    @InjectMocks
    private ControladorCompra controladorCompra;

    @Test
    public void queSePuedaGuardarUnaCompraYMostrarla(){
        //Preparacion
        Compra compraFormulario=new Compra();
        Cotizacion cotizacionFormulario=new Cotizacion(TipoMoneda.DOLAR,200.00);
        Compra compraPersistida=new Compra();
        compraFormulario.setCotizacion(cotizacionFormulario);
        compraPersistida.setCotizacion(cotizacionFormulario);
        when(servicioCompra.guardarCompra(compraFormulario)).thenReturn(compraPersistida);

        //Ejecucion
        ModelAndView mav=controladorCompra.guardarCompra(compraFormulario);

        //Contrastacion
        assertEquals(compraPersistida,mav.getModel().get("compra"));
        assertEquals("compraGuardada",mav.getViewName());
    }

    @Test
    public void queSePuedaConsultarPesosRequeridosEnBaseALaCotizacionAntesDeComprar(){
        //Preparacion
            Cotizacion cotizacion=new Cotizacion(TipoMoneda.DOLAR,200.00);
            Compra compraFormulario=new Compra();
            compraFormulario.setCotizacion(cotizacion);
            Double cantidadDolaresAComprar=1000.00;
            compraFormulario.setCantidad(cantidadDolaresAComprar);
            Double cantidadPesosRequeridos=200000.00;

            CompraDTO compraDTO=new CompraDTO();
            compraDTO.setCompra(compraFormulario);
            compraDTO.setCotizacion(cotizacion);

            when(servicioCompra.calcularCotizacion(compraDTO)).thenReturn(cantidadPesosRequeridos);

        //Ejecucion
        ModelAndView mav=controladorCompra.calcularPesosRequeridos(compraDTO);

        //Contrastacion
        assertEquals(mav.getViewName(),"comprar");
        assertEquals(cantidadPesosRequeridos,mav.getModel().get("pesosEstimados"));
    }
}
