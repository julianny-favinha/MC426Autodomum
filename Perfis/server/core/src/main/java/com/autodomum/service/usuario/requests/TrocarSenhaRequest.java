package com.autodomum.service.usuario.requests;

/**
 * @author sabrina on 26/05/16.
 */
public class TrocarSenhaRequest {

    private String username;

    private String senhaAntiga;

    private String novaSenha;

    public String getUsername() {
        return username;
    }

    public String getSenhaAntiga() {
        return senhaAntiga;
    }

    public String getNovaSenha() {
        return novaSenha;
    }
}
