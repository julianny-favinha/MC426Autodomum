package com.autodomum.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * @author sabrina on 21/06/16.
 */
public enum EstadoChuva implements EnumModel {

    SECO(1),
    CHUVA_FRACA(2),
    CHUVA_FORTE(3);

    private int id;

    EstadoChuva(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @JsonCreator
    public static EstadoChuva buscaPorNome(String estadoChuva) {
        return Arrays.stream(EstadoChuva.values())
                .filter(t -> t.name().equals(estadoChuva)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Nao existe estado de chuva com nome"));
    }

    public static EstadoChuva buscaPorId(int id) {
        return Arrays.stream(EstadoChuva.values())
                .filter(t -> t.id == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Nao existe estado de chuva com id"));
    }
}
