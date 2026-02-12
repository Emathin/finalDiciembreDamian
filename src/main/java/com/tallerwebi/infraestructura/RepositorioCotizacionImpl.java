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
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT c.valor FROM Cotizacion c WHERE c.tipoMoneda = :tipo", Double.class)
                .setParameter("tipo", tipoMoneda)
                .getSingleResult();
    }
    @Override
    public void guardarCotizacion(Cotizacion cotizacion1) {
        Session sesion=sessionFactory.getCurrentSession();
        sesion.save(cotizacion1);
    }


}
