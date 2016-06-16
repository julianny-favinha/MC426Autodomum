package com.autodomum.modelo;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author sabrina on 27/05/16.
 */
public class HistoricoToldo {

    private Integer id;
    private boolean fechado;
    private LocalDateTime data;
    private Toldo toldo;

    public HistoricoToldo() {
        data = LocalDateTime.now();
    }

    public HistoricoToldo(boolean fechado, LocalDateTime data, Toldo toldo) {
        this.fechado = fechado;
        this.data = data;
        this.toldo = toldo;
    }

    public HistoricoToldo(Integer id, boolean fechado, LocalDateTime data, Toldo toldo) {
        this.id = id;
        this.fechado = fechado;
        this.data = data;
        this.toldo = toldo;
    }

    public Integer getId() {
        return id;
    }

    public boolean isFechado() {
        return fechado;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Toldo getToldo() {
        return toldo;
    }
}
