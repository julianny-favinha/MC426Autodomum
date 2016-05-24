package com.unicamp.modelo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;

/**
 * @author sabrina on 16/05/16.
 */
@JsonSerialize
@JsonDeserialize
public class Pessoa {

    private String email;
    private String senha; //mudar no esquema
    private String nome;
    private String sobrenome;
    private Celular celular;
    private LocalDate dataDeNascimento;

    public Pessoa() {
    }

    public Pessoa(String email, String senha, String nome, String sobrenome, Celular celular, LocalDate data_de_nascimento) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.sobrenome = sobrenome;
        this.celular = celular;
        this.dataDeNascimento = data_de_nascimento;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public Celular getCelular() {
        return celular;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

}
