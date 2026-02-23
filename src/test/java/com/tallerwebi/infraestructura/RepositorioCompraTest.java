package com.tallerwebi.infraestructura;

import com.tallerwebi.config.HibernateConfig;
import com.tallerwebi.config.SpringWebConfig;
import com.tallerwebi.dominio.*;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith; // <--- Importante 1
import org.springframework.beans.factory.annotation.Autowired; // <--- Importante 2
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension; // <--- Importante 3
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, SpringWebConfig.class})
@WebAppConfiguration
@Transactional
@Rollback
public class RepositorioCompraTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCompra repositorioCompra;

    @Autowired
    public RepositorioCotizacion repositorioCotizacion;

    @Test
    public void testQueSePuedaGuardarUnaCompra() {
        Compra compra1 = new Compra();
        Cotizacion cotizacion1 = new Cotizacion(TipoMoneda.DOLAR, 100.0);
        compra1.setCotizacion(cotizacion1);

        Compra guardada = repositorioCompra.guardarCompra(compra1);

        assertThat(guardada.getId(), org.hamcrest.Matchers.notNullValue());
        Assertions.assertEquals(TipoMoneda.DOLAR, guardada.getCotizacion().getTipoMoneda());
        Assertions.assertEquals(100.0, guardada.getCotizacion().getValor());
    }

    @Test
    public void queSePuedaObtenerUnaCotizacionPorElTipoDeMoneda() {
        Cotizacion cotizacion1 = new Cotizacion(TipoMoneda.EURO, 150.0);
        repositorioCotizacion.guardarCotizacion(cotizacion1);

        Double valorObtenido = repositorioCotizacion.obtenerCotizacion(TipoMoneda.EURO);

        Assertions.assertEquals(150.0, valorObtenido);

    }

    @Test
    public void queSePuedaObtenerUnaCotizacionCompletaPorElTipoDeMoneda() {
        Cotizacion cotizacion1 = new Cotizacion(TipoMoneda.EURO, 50.0);
        Cotizacion cotizacion2 = new Cotizacion(TipoMoneda.DOLAR, 100.0);
        repositorioCotizacion.guardarCotizacion(cotizacion1);
        repositorioCotizacion.guardarCotizacion(cotizacion2);

        Cotizacion cotizacionObtenida = repositorioCotizacion.obtenerCotizacionCompleta(TipoMoneda.EURO);
        Cotizacion cotizacionObtenida2 = repositorioCotizacion.obtenerCotizacionCompleta(TipoMoneda.DOLAR);

        assertThat(cotizacionObtenida.getIdCotizacion(), org.hamcrest.Matchers.notNullValue());
        assertThat(cotizacionObtenida2.getIdCotizacion(), org.hamcrest.Matchers.notNullValue());
        Assertions.assertEquals(TipoMoneda.EURO, cotizacionObtenida.getTipoMoneda());
        Assertions.assertEquals(50.0, cotizacionObtenida.getValor());
        Assertions.assertEquals(TipoMoneda.DOLAR, cotizacionObtenida2.getTipoMoneda());
        Assertions.assertEquals(100.0, cotizacionObtenida2.getValor());

    }
}