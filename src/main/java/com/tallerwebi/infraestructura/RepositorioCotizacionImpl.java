package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Cotizacion;
import com.tallerwebi.dominio.RepositorioCotizacion;
import com.tallerwebi.dominio.TipoMoneda;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class RepositorioCotizacionImpl implements RepositorioCotizacion {

    @Autowired
    private SessionFactory sessionFactory;

     public RepositorioCotizacionImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Double obtenerCotizacion(TipoMoneda tipoMoneda) {
        // Al ser un examen, usamos la forma más directa y limpia
        return sessionFactory.getCurrentSession()
                // Variante en el Repositorio si lo anterior falla
                .createQuery("SELECT c.valor FROM Cotizacion c WHERE c.tipoMoneda = :tipo", Double.class)
                .setParameter("tipo", tipoMoneda.name()) // .name() devuelve "DOLAR" como String puro
                .getSingleResult();
    }
    @Override
    public void guardarCotizacion(Cotizacion cotizacion1) {
        Session sesion=sessionFactory.getCurrentSession();
        sesion.save(cotizacion1);
    }


}
