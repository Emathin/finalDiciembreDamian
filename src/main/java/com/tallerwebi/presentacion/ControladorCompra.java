package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Compra;
import com.tallerwebi.dominio.Cotizacion;
import com.tallerwebi.dominio.ServicioCompra;
import com.tallerwebi.dominio.TipoMoneda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class ControladorCompra {
@Autowired
    private final ServicioCompra servicioCompra;

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
    public ModelAndView guardarCompra(Compra compraFormulario) {
        Compra compraPersistida= servicioCompra.guardarCompra(compraFormulario);
        ModelAndView mav=new ModelAndView("compraGuardada");
        mav.addObject("compra",compraPersistida);
        return mav;
    }

    @PostMapping("/calcularPesosRequeridos")
    public ModelAndView calcularPesosRequeridos(@ModelAttribute("compra") CompraDTO compraFormulario) {
    Double resultado=servicioCompra.calcularCotizacion(compraFormulario);
    ModelAndView mav=new ModelAndView("comprar");
    mav.addObject("compra",compraFormulario);
    mav.addObject("pesosEstimados",resultado);
    return mav;
    }

    @PostMapping("/consultarCotizacion")
    public ModelAndView consultar(@ModelAttribute("compraDTO") CompraDTO compraDto) {
        // Si esto imprime null, el problema es el HTML
        System.out.println("Moneda: " + (compraDto.getCotizacion() != null ? compraDto.getCotizacion().getTipoMoneda() : "Objeto Cotizacion nulo"));
        ModelAndView mav = new ModelAndView("comprar");
        Double valorMoneda = servicioCompra.calcularCotizacion(compraDto); // Esto vendría de tu servicio de cotizaciones
        Double resultado = compraDto.getCompra().getCantidad() * valorMoneda;

        mav.addObject("pesosEstimados", resultado);
        mav.addObject("compraDTO", compraDto); // Devolvemos el objeto para mantener los datos en los inputs

        return mav;
    }
}
