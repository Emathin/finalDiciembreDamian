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
import java.util.ArrayList;
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
    public void queSePuedaGuardarUnaCompraYDevuelvaMensajeExito() {
        // Preparación
        CompraDTO compraFormulario = new CompraDTO();
        // Simulamos que el servicio guarda correctamente
        // (Asegúrate de que el servicio devuelva algo si tu lógica lo requiere)

        // Ejecución
        ResponseEntity<Map<String, String>> respuesta = controladorCompra.guardarCompra(compraFormulario);

        // Contrastación
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertNotNull(respuesta.getBody());
        assertEquals("¡La compra se ha guardado exitosamente!", respuesta.getBody().get("mensaje"));

        // Verificamos que el servicio fue llamado exactamente una vez
        verify(servicioCompra, times(1)).guardarCompra(compraFormulario);
    }

    @Test

    public void queSePuedaConsultarPesosRequeridosEnBaseALaCotizacionAntesDeComprar(){
        //Preparacion
            Cotizacion cotizacion=new Cotizacion(TipoMoneda.DOLAR,200.00);

            Double cantidadPesosRequeridos=200000.00;

            CompraDTO compraDTO=new CompraDTO();

            compraDTO.setCantidadDeDivisasCompradas(1000.00);

            compraDTO.setTipoMoneda(cotizacion.getTipoMoneda());

            when(servicioCompra.obtenerCotizacion(compraDTO)).thenReturn(cantidadPesosRequeridos);


        //Ejecucion
        ModelAndView mav=controladorCompra.calcularPesosRequeridos(compraDTO);

        //Contrastacion
        assertEquals(mav.getViewName(),"comprar");
        assertEquals(cantidadPesosRequeridos,mav.getModel().get("pesosEstimados"));
    }

    @Test
    public void queSePuedanObtenerTodasLasCompras(){
        //Preparacion
        CompraDTO compraDTO1=new CompraDTO();
        CompraDTO compraDTO2=new CompraDTO();

        List<CompraDTO> compraDTOList=new ArrayList<>();
        compraDTOList.add(compraDTO1);
        compraDTOList.add(compraDTO2);

        when(servicioCompra.obtenerTodasLasCompras()).thenReturn(compraDTOList);
        //Ejecucion
        ModelAndView mav=new ModelAndView("compras");
        mav.addObject("compras",controladorCompra.obtenerTodasLasCompras());

        //Contrastacion
        assertEquals("compras",mav.getViewName());
        assertEquals(2,((java.util.List<CompraDTO>) mav.getModel().get("compras")).size());
        assertEquals(compraDTO1,((java.util.List<CompraDTO>) mav.getModel().get("compras")).get(0));
        assertEquals(compraDTO2,((java.util.List<CompraDTO>) mav.getModel().get("compras")).get(1));
    }
}
