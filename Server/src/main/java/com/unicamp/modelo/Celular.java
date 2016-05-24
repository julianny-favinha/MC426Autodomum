package com.unicamp.modelo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author sabrina on 16/05/16.
 */
@JsonSerialize
@JsonDeserialize
public class Celular {

    private int ddi;
    private int ddd;
    private int numero;

    public Celular() {
    }

    public Celular(int ddi, int ddd, int numero) {
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = numero;
    }

    public int getDdi() {
        return ddi;
    }

    public int getDdd() {
        return ddd;
    }

    public int getNumero() {
        return numero;
    }
}
