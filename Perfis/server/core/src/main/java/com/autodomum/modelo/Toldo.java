package com.autodomum.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * @author sabrina on 27/05/16.
 */
public enum Toldo implements EnumModel {
    VARAL(1),
    JARDIM(2);

    private int id;

    Toldo(int id) {

        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @JsonCreator
    public static Toldo buscaPorNome(String toldo) {
        return Arrays.stream(Toldo.values())
                .filter(t -> t.name().equals(toldo)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Nao existe toldo com nome"));
    }

    public static Toldo buscaPorId(int id) {
        return Arrays.stream(Toldo.values())
                .filter(t -> t.id == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Nao existe toldo com id"));
    }
}
