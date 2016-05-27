package com.autodomum.aplicacao.controller.requests;

/**
 * @author sabrina on 26/05/16.
 */
public class LoginRequest {

    private String email;

    private String senha;

    public LoginRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
