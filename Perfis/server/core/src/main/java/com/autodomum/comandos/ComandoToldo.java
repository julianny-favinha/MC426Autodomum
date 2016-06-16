package com.autodomum.comandos;

import com.autodomum.modelo.Toldo;

/**
 * @author sabrina on 13/06/16.
 */
public class ComandoToldo extends Comando {

    private boolean estendido;

    private boolean automatico;

    private Toldo toldo;

    public ComandoToldo() {
        this.funcionalidade = Funcionalidade.TOLDO;
    }

    public boolean getEstendido() {
        return estendido;
    }

    public boolean isAutomatico() {
        return automatico;
    }

    public Toldo getToldo() {
        return toldo;
    }

}
