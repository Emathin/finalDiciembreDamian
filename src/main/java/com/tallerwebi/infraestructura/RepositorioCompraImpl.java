package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Compra;
import com.tallerwebi.dominio.RepositorioCompra;
import com.tallerwebi.dominio.TipoMoneda;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RepositorioCompraImpl implements RepositorioCompra {
    @Autowired
    final SessionFactory sessionFactory;

    @Autowired
    public RepositorioCompraImpl(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;

    }

    @Override
    public Compra guardarCompra(Compra compra) {
        Session session=sessionFactory.getCurrentSession();
        session.save(compra);
        return compra;
    }

    @Override
    public List<Compra> obtenerTodasLasCompras() {
        return List.of();
    }


}
