package com.tallerwebi.infraestructura;

import com.tallerwebi.config.HibernateConfig;
import com.tallerwebi.config.SpringWebConfig;
import com.tallerwebi.dominio.Compra;
import com.tallerwebi.dominio.RepositorioCompra;
import com.tallerwebi.dominio.TipoMoneda;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith; // <--- Importante 1
import org.springframework.beans.factory.annotation.Autowired; // <--- Importante 2
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension; // <--- Importante 3
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;

// FALTABA ESTO: Conecta JUnit 5 con Spring
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, SpringWebConfig.class})
@WebAppConfiguration
@Transactional
public class RepositorioCompraTest {

    @Autowired // <--- Opcional si lo vas a usar, pero recomendado
    private SessionFactory sessionFactory;

    @Autowired // <--- FALTABA ESTO: Inyecta la dependencia
    public RepositorioCompra repositorioCompra;

    @Test
    public void testQueSePuedaGuardarUnaCompra() {
        Compra compra1=new Compra(TipoMoneda.DOLAR, 100.0);

        Compra guardada=repositorioCompra.guardarCompra(compra1);

        assertThat(guardada.getId(), org.hamcrest.Matchers.notNullValue());
        Assertions.assertEquals(TipoMoneda.DOLAR, guardada.getTipoMoneda());
        Assertions.assertEquals(100.0, guardada.getCantidad());
    }

}