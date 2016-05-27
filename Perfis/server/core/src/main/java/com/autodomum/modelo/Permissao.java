package com.autodomum.modelo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
public class Permissao {

	private int id;
    private String nome;
    private String sistema_externo;

    public Permissao() {
    }

    public Permissao(int id, String nome, String sistema_externo) {
        this.id = id;
        this.nome = nome;
        this.sistema_externo = sistema_externo;
    }

    public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSistema_externo() {
		return sistema_externo;
	}

	public static Builder builder() {
        return new Builder();
    }



    public static class Builder {
    	private int id;
        private String nome;
        private String sistema_externo;


        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder sistema_externo(String sistema_externo) {
            this.sistema_externo = sistema_externo;
            return this;
        }

        public Permissao build() {
            return new Permissao(id, nome, sistema_externo);
        }
    }
}

