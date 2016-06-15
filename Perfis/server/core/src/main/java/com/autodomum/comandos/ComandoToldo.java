package com.autodomum.comandos;

import com.autodomum.modelo.Toldo;

/**
 * @author sabrina on 13/06/16.
 */
public class ComandoToldo extends Comando {

    private EstadosToldo estado;

    private Toldo toldo;

    public ComandoToldo() {
        this.funcionalidade = Funcionalidade.TOLDO;
    }

    public EstadosToldo getEstado() {
        return estado;
    }

    public Toldo getToldo() {
        return toldo;
    }

}
