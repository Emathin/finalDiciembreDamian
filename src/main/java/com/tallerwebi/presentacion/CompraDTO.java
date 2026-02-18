    package com.tallerwebi.presentacion;

    import com.tallerwebi.dominio.TipoMoneda;

    import java.time.LocalDateTime;

    public class CompraDTO {

        private TipoMoneda tipoMoneda;
        private Double cotizacion;
        private Double precioPagado;
        private Double cantidadDeDivisasCompradas;
        private LocalDateTime fechaCompra= LocalDateTime.now().truncatedTo(java.time.temporal.ChronoUnit.SECONDS);

      public CompraDTO() {
        }

        public CompraDTO(TipoMoneda tipoMoneda, Double cotizacion, Double precioPagado, Double cantidadDeDivisasCompradas) {
            this.tipoMoneda = tipoMoneda;
            this.cotizacion = cotizacion;
            this.precioPagado = precioPagado;
            this.cantidadDeDivisasCompradas = cantidadDeDivisasCompradas;
        }

        public TipoMoneda getTipoMoneda() {
            return tipoMoneda;
        }

        public void setTipoMoneda(TipoMoneda tipoMoneda) {
            this.tipoMoneda = tipoMoneda;
        }

        public Double getCotizacion() {
            return cotizacion;
        }

        public void setCotizacion(Double cotizacion) {
            this.cotizacion = cotizacion;
        }

        public Double getPrecioPagado() {
            return precioPagado;
        }

        public void setPrecioPagado(Double precioPagado) {
            this.precioPagado = precioPagado;
        }

        public Double getCantidadDeDivisasCompradas() {
            return cantidadDeDivisasCompradas;
        }

        public void setCantidadDeDivisasCompradas(Double cantidadDeDivisasCompradas) {
            this.cantidadDeDivisasCompradas = cantidadDeDivisasCompradas;
        }

        public LocalDateTime getFechaCompra() {return fechaCompra;}

        public void setFechaCompra(LocalDateTime fechaCompra) {this.fechaCompra = fechaCompra;}
    }
