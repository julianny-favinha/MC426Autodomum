package com.autodomum.service.usuario.results;

/**
 * @author sabrina on 26/05/16.
 */
public class DefaultResponse {

    private boolean sucesso;

    public DefaultResponse(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public boolean isSucesso() {
        return sucesso;
    }
}
