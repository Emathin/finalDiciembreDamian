package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Compra;
import com.tallerwebi.dominio.ServicioCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class ControladorCompra {

    private final ServicioCompra servicioCompra;

    @Autowired
    public ControladorCompra(ServicioCompra servicioCompra) {
        this.servicioCompra = servicioCompra;
    }

    @GetMapping("/comprar")
    public ModelAndView comprar(){
        ModelAndView mav=new ModelAndView("comprar");
        mav.addObject("compra",new Compra());
        return mav;
    }

    @RequestMapping("/guardar")
    public ModelAndView guardarCompra(Compra compraFormulario) {

        Compra compraPersistida= servicioCompra.guardarCompra(compraFormulario);
        ModelAndView mav=new ModelAndView("compraGuardada");
        mav.addObject("compra",compraPersistida);
        return mav;

    }
}
