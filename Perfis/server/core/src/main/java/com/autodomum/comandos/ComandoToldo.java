package com.autodomum.comandos;

import com.autodomum.modelo.Toldo;

/**
 * @author sabrina on 13/06/16.
 */
public class ComandoToldo extends Comando {

    private boolean aberto;

    private boolean automatico;

    private Toldo toldo;

    public ComandoToldo() {
        this.funcionalidade = Funcionalidade.TOLDO;
    }

    public boolean isAberto() {
        return aberto;
    }

    public boolean isAutomatico() {
        return automatico;
    }

    public Toldo getToldo() {
        return toldo;
    }

}
