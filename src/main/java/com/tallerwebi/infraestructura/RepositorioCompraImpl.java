package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Compra;
import com.tallerwebi.dominio.RepositorioCompra;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioCompraImpl implements RepositorioCompra {
    @Autowired
    final SessionFactory sessionFactory;

    @Autowired
    public RepositorioCompraImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardar(Compra compra) {
        Session session=sessionFactory.getCurrentSession();
        session.save(compra);
    }
}
