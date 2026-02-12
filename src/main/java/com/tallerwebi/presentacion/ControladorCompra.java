package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.infraestructura.RepositorioCotizacionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class ControladorCompra {
@Autowired
    private final ServicioCompra servicioCompra;

    @Autowired
    private RepositorioCotizacion repositorioCotizacionImpl;

    @Autowired
    public ControladorCompra(ServicioCompra servicioCompra) {
        this.servicioCompra = servicioCompra;
    }

    @GetMapping("/comprar")
    public ModelAndView comprar(){
        ModelAndView mav=new ModelAndView("comprar");
        CompraDTO compraDTO=new CompraDTO();
        mav.addObject("compraDTO",compraDTO);
        return mav;
    }

    @RequestMapping("/guardar")
    public ModelAndView guardarCompra(CompraDTO compraFormulario) {
        CompraDTO compraPersistida= servicioCompra.guardarCompra(compraFormulario);
        ModelAndView mav=new ModelAndView("compraGuardada");
        mav.addObject("compraDTO",compraPersistida);
        return mav;
    }

    @PostMapping("/calcularPesosRequeridos")
    public ModelAndView calcularPesosRequeridos(@ModelAttribute("compra") CompraDTO compraFormulario) {
    Double resultado=servicioCompra.obtenerCotizacion(compraFormulario);
    ModelAndView mav=new ModelAndView("comprar");
    mav.addObject("compra",compraFormulario);
    mav.addObject("pesosEstimados",resultado);
    return mav;
    }

    @PostMapping("/consultarCotizacion")
    public ModelAndView consultar(@ModelAttribute("compraDTO") CompraDTO compraDto) {
        System.out.println("Moneda: " + (compraDto.getCotizacion() != null ? compraDto.getCotizacion().getTipoMoneda() : "Objeto Cotizacion nulo"));
        ModelAndView mav = new ModelAndView("comprar");
        Double valorMoneda = servicioCompra.obtenerCotizacion(compraDto);
        compraDto.getCompra().setPrecioPagado(compraDto.getCompra().getCantidadDeDivisasCompradas() * valorMoneda);
        mav.addObject("compraDTO", compraDto);

        return mav;
    }
}
