package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorCompra {

    @Autowired
    private final ServicioCompra servicioCompra;

    @Autowired
    public ControladorCompra(ServicioCompra servicioCompra) {
        this.servicioCompra = servicioCompra;
    }

    @PostMapping("/guardar") // Mejor usar PostMapping para guardar datos
    public ModelAndView guardarCompra(@ModelAttribute("compraDTO") CompraDTO compraFormulario) {
        compraFormulario.setPrecioPagado(servicioCompra.obtenerCotizacion(compraFormulario));
        servicioCompra.guardarCompra(compraFormulario);
        ModelAndView mav = new ModelAndView("comprar");
        CompraDTO compraDTO = new CompraDTO();
        mav.addObject("compraDTO", compraDTO);
        return mav;
    }

    @PostMapping("/calcularPesosRequeridos")
    public ModelAndView calcularPesosRequeridos(@ModelAttribute("compra") CompraDTO compraFormulario) {
        Double resultado = servicioCompra.obtenerCotizacion(compraFormulario);
        ModelAndView mav = new ModelAndView("comprar");
        mav.addObject("compra", compraFormulario);
        mav.addObject("pesosEstimados", resultado);
        return mav;
    }

    @PostMapping("/consultarCotizacion")
    public ModelAndView consultar(@ModelAttribute("compraDTO") CompraDTO compraDto) {
        System.out.println("Moneda: " + (compraDto.getCotizacion() != null ? compraDto.getTipoMoneda() : "Objeto Cotizacion nulo"));
        ModelAndView mav = new ModelAndView("comprar");
        Double valorMoneda = servicioCompra.obtenerCotizacion(compraDto);
        compraDto.setPrecioPagado(compraDto.getCantidadDeDivisasCompradas() * valorMoneda);
        mav.addObject("compraDTO", compraDto);
        return mav;
    }

    @GetMapping("/obtenerCompras")
    public ModelAndView obtenerTodasLasCompras() {
        ModelAndView mav = new ModelAndView("listadoDeCompras");
        mav.addObject("compras", servicioCompra.obtenerTodasLasCompras());
        return mav;
    }

    @GetMapping("/comprar")
    public ModelAndView comprar() {
        ModelAndView mav = new ModelAndView("comprar");
        CompraDTO compraDTO = new CompraDTO();
        mav.addObject("compraDTO", compraDTO);
        return mav;
    }


}
