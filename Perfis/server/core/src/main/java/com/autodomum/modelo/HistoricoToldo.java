package com.autodomum.modelo;

import java.time.LocalDateTime;

/**
 * @author sabrina on 27/05/16.
 */
public class HistoricoToldo {

    private Integer id;
    private boolean estendido;
    private boolean automatico;
    private LocalDateTime data;
    private Toldo toldo;

    public HistoricoToldo() {
        data = LocalDateTime.now();
    }

    public HistoricoToldo(boolean estendido, boolean automatico, LocalDateTime data, Toldo toldo) {
        this.estendido = estendido;
        this.automatico = automatico;
        this.data = data;
        this.toldo = toldo;
    }

    public HistoricoToldo(Integer id, boolean fechado, boolean automatico, LocalDateTime data, Toldo toldo) {
        this.id = id;
        this.estendido = fechado;
        this.automatico = automatico;
        this.data = data;
        this.toldo = toldo;
    }

    public Integer getId() {
        return id;
    }

    public boolean isEstendido() {
        return estendido;
    }

    public boolean isAutomatico() {
        return automatico;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Toldo getToldo() {
        return toldo;
    }
}
