package com.tallerwebi.infraestructura;

import com.tallerwebi.config.HibernateConfig;
import com.tallerwebi.config.SpringWebConfig;
import com.tallerwebi.dominio.Cotizacion;
import com.tallerwebi.dominio.RepositorioCotizacion;
import com.tallerwebi.dominio.TipoMoneda;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, SpringWebConfig.class})
@WebAppConfiguration
@Transactional
@Rollback
public class RepositorioCotizacionTest {

    @Autowired
    private RepositorioCotizacion repositorioCotizacion;


    @Test
    public void queSePuedaGuardarUnaCoticion(){
        Cotizacion cotizacion1=new Cotizacion(TipoMoneda.LIBRA, 1500.0);

        repositorioCotizacion.guardarCotizacion(cotizacion1);

        Double valorCotizacion=repositorioCotizacion.obtenerCotizacion(TipoMoneda.LIBRA);

        Assertions.assertEquals(1500.0, valorCotizacion);
    }




}
