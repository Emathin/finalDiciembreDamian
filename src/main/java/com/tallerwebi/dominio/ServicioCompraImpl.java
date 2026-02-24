package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.CompraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioCompraImpl implements ServicioCompra {

    private final RepositorioCompra repositorioCompra;

    private final RepositorioCotizacion repositorioCotizacion;

    @Autowired
    public ServicioCompraImpl(RepositorioCompra repositorioCompra, RepositorioCotizacion repositorioCotizacion) {
        this.repositorioCompra = repositorioCompra;
        this.repositorioCotizacion = repositorioCotizacion;
    }

    @Override
    @Transactional
    public void guardarCompra(CompraDTO compraDTO) {

        Cotizacion cotizacion = repositorioCotizacion.obtenerCotizacionCompleta(compraDTO.getTipoMoneda());

        Compra compra = new Compra();

        compra.setCantidadDeDivisasCompradas(compraDTO.getCantidadDeDivisasCompradas());
        compra.setCotizacion(cotizacion);
        compra.setPrecioPagado(compraDTO.getPrecioPagado());
        compra.setFechaCompra(compraDTO.getFechaCompra());

        repositorioCompra.guardarCompra(compra);
    }

    @Override
    @Transactional
    public Double   obtenerCotizacion(CompraDTO compraFormularioDTO) {
        return repositorioCotizacion.obtenerCotizacion(compraFormularioDTO.getTipoMoneda());
    }

    @Override
    public List<CompraDTO> obtenerTodasLasCompras() {
        List<Compra> comprasObtenidas =repositorioCompra.obtenerTodasLasCompras();
        return pasarCompraADTO(comprasObtenidas);
    }

    @Override
    public List<CompraDTO> obtenerComprasPorMoneda(TipoMoneda tipoMoneda) {
        return pasarCompraADTO(repositorioCompra.obtenerComprasPorMoneda(tipoMoneda));
    }

    private List<CompraDTO> pasarCompraADTO(List<Compra> comprasObtenidas) {
        List<CompraDTO> DTOList = new ArrayList<>();
        for (Compra c : comprasObtenidas) {
            CompraDTO dto = new CompraDTO();

            dto.setTipoMoneda(c.getCotizacion().getTipoMoneda());
            dto.setPrecioPagado(c.getPrecioPagado());
            dto.setCotizacion(c.getCotizacion().getValor());
            dto.setCantidadDeDivisasCompradas(c.getCantidadDeDivisasCompradas());

            DTOList.add(dto);
        }
        return DTOList;
    }

}
