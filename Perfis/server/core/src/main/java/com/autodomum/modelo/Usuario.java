package com.autodomum.modelo;

import java.util.ArrayList;
import java.util.List;

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
    private Long rfid;
    private List<Integer> permissoes;

    public Usuario() {

    }

    public Usuario(String username, String nome, String senha, Long rfid, List<Integer> permissoes) {
        this.username = username;
        this.nome = nome;
        this.senha = senha;
        this.rfid = rfid;
        this.permissoes = permissoes;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public Long getRfid() {
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

    public List<Integer> getPermissoes() {
        return permissoes;
    }

    public static class Builder {
        private String username;
        private String nome;
        private String senha;
        private Long rfid;
        private List<Integer> permissoes;

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder senha(String senha) {
            this.senha = senha;
            return this;
        }

        public Builder rfid(Long rfid) {
            this.rfid = rfid;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder permissoes(List<Integer> permissoes) {
            this.permissoes = permissoes;
            return this;
        }

        public Usuario build() {
            if(permissoes == null) {
                permissoes = new ArrayList<>();
            }
            return new Usuario(username, nome, senha, rfid, permissoes);
        }
    }
}
