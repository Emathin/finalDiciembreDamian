package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Compra;
import com.tallerwebi.dominio.ServicioCompra;
import com.tallerwebi.dominio.TipoMoneda;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
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
        Compra compraFormulario=new Compra(TipoMoneda.DOLAR,1000.00);
        Compra compraPersistida=new Compra(1L,TipoMoneda.DOLAR,1000.00);
        when(servicioCompra.guardarCompra(compraFormulario)).thenReturn(compraPersistida);

        //Ejecucion
        ModelAndView mav=controladorCompra.guardarCompra(compraFormulario);

        //Contrastacion
        assertEquals(compraPersistida,mav.getModel().get("compra"));
        assertEquals("compraGuardada",mav.getViewName());
    }

}
