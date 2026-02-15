package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.CompraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioCompraImpl implements ServicioCompra {

    @Autowired
    private RepositorioCompra repositorioCompra;

    @Autowired
    private RepositorioCotizacion repositorioCotizacion;

    @Autowired
    public ServicioCompraImpl(RepositorioCompra repositorioCompra, RepositorioCotizacion repositorioCotizacion) {
        this.repositorioCompra = repositorioCompra;
        this.repositorioCotizacion = repositorioCotizacion;
    }
    @Override
    @Transactional
    public Compra guardarCompra(CompraDTO compraDTO) {

        Cotizacion cotizacion=repositorioCotizacion.obtenerCotizacionCompleta(compraDTO.getTipoMoneda());

        Compra compra=new Compra();

        compra.setCantidadDeDivisasCompradas(compraDTO.getCantidadDeDivisasCompradas());
        compra.setCotizacion(cotizacion);
        compra.setPrecioPagado(compraDTO.getPrecioPagado());

        repositorioCompra.guardarCompra(compra);

        return compra;
    }

    @Override
    @Transactional
    public Double obtenerCotizacion(CompraDTO compraFormularioDTO) {
        return repositorioCotizacion.obtenerCotizacion(compraFormularioDTO.getTipoMoneda());
    }

    @Override
    public List<CompraDTO> obtenerTodasLasCompras() {
        List<Compra> comprasObtenidas=new ArrayList<>();
        comprasObtenidas=repositorioCompra.obtenerTodasLasCompras();
        List<CompraDTO> compraDTOList= pasarCompraADTO(comprasObtenidas);
        return compraDTOList;
    }

    private List<CompraDTO> pasarCompraADTO(List<Compra> comprasObtenidas) {
        List<CompraDTO> DTOList=new ArrayList<>();
        for(Compra c:comprasObtenidas){
            CompraDTO dto=new CompraDTO();
            dto.setCompra(comprasObtenidas.getClass());

        }
    }
}
