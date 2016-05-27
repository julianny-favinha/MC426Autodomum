package com.autodomum.modelo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author sabrina on 16/05/16.
 */
@JsonSerialize
@JsonDeserialize
public class Usuario {

    private String username;
    private String nome;
    private String senha;
    private int rfid;

    public Usuario() {
    }

    public Usuario(String username, String nome, String senha, int rfid) {
        this.username = username;
        this.nome = nome;
        this.senha = senha;
        this.rfid = rfid;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public int getRfid() {
        return rfid;
    }

    public String getUsername() {
        return username;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static class Builder {
        private String username;
        private String nome;
        private String senha;
        private int rfid;

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder senha(String senha) {
            this.senha = senha;
            return this;
        }

        public Builder rfid(int rfid) {
            this.rfid = rfid;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Usuario build() {
            return new Usuario(username, nome, senha, rfid);
        }
    }
}
