package com.unicamp.modelo.builder;

import com.google.common.base.Preconditions;
import com.unicamp.modelo.Celular;
import com.unicamp.modelo.Pessoa;

import java.time.LocalDate;

/**
 * @author sabrina on 16/05/16.
 */
public class PessoaBuilder {

    private String email;
    private String senha;
    private String nome;
    private String sobrenome;
    private Celular celular;
    private LocalDate dataDeNascimento;

    public PessoaBuilder email(String email) {
        this.email = email;
        return this;
    }

    public PessoaBuilder senha(String senha) {
        this.senha = senha;
        return this;
    }

    public PessoaBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public PessoaBuilder sobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
        return this;
    }

    public PessoaBuilder celular(Celular celular) {
        this.celular = celular;
        return this;
    }

    public PessoaBuilder dataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
        return this;
    }

    public Pessoa build() {
        Preconditions.checkNotNull(email);
        Preconditions.checkNotNull(senha);
        Preconditions.checkNotNull(nome);
        Preconditions.checkNotNull(sobrenome);
        Preconditions.checkNotNull(celular);
        Preconditions.checkNotNull(dataDeNascimento);

        return new Pessoa(email, senha, nome, sobrenome, celular, dataDeNascimento);
    }

}
