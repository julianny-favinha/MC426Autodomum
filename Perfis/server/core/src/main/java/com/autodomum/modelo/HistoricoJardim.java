package com.autodomum.modelo;

import java.time.LocalDateTime;

/**
 * @author sabrina on 21/06/16.
 */
public class HistoricoJardim {

    private Integer id;

    private EstadoChuva estadoChuva;

    private float temperatura;

    private LocalDateTime data;

    public HistoricoJardim() {
        this.data = LocalDateTime.now();
    }

    public HistoricoJardim(Integer id, EstadoChuva estadoChuva, float temperatura, LocalDateTime data) {
        this.estadoChuva = estadoChuva;
        this.temperatura = temperatura;
        this.data = data;
    }

    public EstadoChuva getEstadoChuva() {
        return estadoChuva;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public LocalDateTime getData() {
        return data;
    }
}
