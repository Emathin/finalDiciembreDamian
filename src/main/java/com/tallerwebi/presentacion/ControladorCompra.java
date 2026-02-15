package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.infraestructura.RepositorioCotizacionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @GetMapping("/obtenerCompras")
    public List<CompraDTO> obtenerTodasLasCompras() {
        return servicioCompra.obtenerTodasLasCompras();
    }

    @GetMapping("/comprar")
    public ModelAndView comprar(){
        ModelAndView mav=new ModelAndView("comprar");
        CompraDTO compraDTO=new CompraDTO();
        mav.addObject("compraDTO",compraDTO);
        return mav;
    }

    @PostMapping("/guardar") // Mejor usar PostMapping para guardar datos
    public ResponseEntity<Map<String, String>> guardarCompra(@RequestBody CompraDTO compraFormulario) {
        // 1. Guardamos la compra
        servicioCompra.guardarCompra(compraFormulario);

        // 2. Preparamos la respuesta
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "¡La compra se ha guardado exitosamente!");

        // 3. Devolvemos un 200 OK con el mapa convertido a JSON
        return ResponseEntity.ok(respuesta);
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
        System.out.println("Moneda: " + (compraDto.getCotizacion() != null ? compraDto.getTipoMoneda() : "Objeto Cotizacion nulo"));
        ModelAndView mav = new ModelAndView("comprar");
        Double valorMoneda = servicioCompra.obtenerCotizacion(compraDto);
        compraDto.setPrecioPagado(compraDto.getCantidadDeDivisasCompradas() * valorMoneda);
        mav.addObject("compraDTO", compraDto);

        return mav;
    }


}
