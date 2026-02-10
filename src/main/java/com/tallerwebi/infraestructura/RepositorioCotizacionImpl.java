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
/*
    @Override
    public Double obtenerCotizacion(TipoMoneda tipoMoneda) {
        Session sesion=sessionFactory.getCurrentSession();
        return   sesion.createQuery("SELECT c.valor FROM Cotizacion c WHERE UPPER(c.tipoMoneda) = UPPER(:tipoMoneda)", Double.class)
                .setParameter("tipoMoneda", tipoMoneda)
                .getSingleResult();
    }

    @Override
    public Double obtenerCotizacion(TipoMoneda tipoMoneda) {
        Session sesion = sessionFactory.getCurrentSession();
        return sesion.createQuery(
                        "SELECT c.valor FROM Cotizacion c WHERE c.tipoMoneda = :tipoMoneda", Double.class)
                .setParameter("tipoMoneda", tipoMoneda) // Hibernate se encarga de la conversión
                .getSingleResult();
    }
    @Override
    public Double obtenerCotizacion(TipoMoneda tipoMoneda) {
        Session sesion = sessionFactory.getCurrentSession();
        return sesion.createQuery(
                        "SELECT c.valor FROM Cotizacion c WHERE c.tipoMoneda = :tipo", Double.class)
                .setParameter("tipo", tipoMoneda) // Hibernate debería convertirlo, pero...
                .getSingleResult();
    }

    @Override
    public Double obtenerCotizacion(TipoMoneda tipoMoneda) {
        Session sesion = sessionFactory.getCurrentSession();
        return sesion.createQuery(

                        "SELECT c.valor FROM Cotizacion c WHERE CAST(c.tipoMoneda AS string) = :tipo", Double.class)
                .setParameter("tipo", tipoMoneda.toString())
                .getSingleResult();
    }*/

    @Override
    public Double obtenerCotizacion(TipoMoneda tipoMoneda) {
        Session sesion = sessionFactory.getCurrentSession();

        // Pasamos el enum a String para la comparación
        String monedaBusqueda = tipoMoneda.name();

        return sesion.createQuery(
                        "SELECT c.valor FROM Cotizacion c WHERE STR(c.tipoMoneda) LIKE :tipo", Double.class)
                .setParameter("tipo", "%" + monedaBusqueda + "%")
                .getSingleResult();
    }
    @Override
    public void guardarCotizacion(Cotizacion cotizacion1) {
        Session sesion=sessionFactory.getCurrentSession();
        sesion.save(cotizacion1);
    }


}
