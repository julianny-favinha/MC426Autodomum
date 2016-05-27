package com.autodomum.service.usuario.results;

import java.util.Optional;

/**
 * @author sabrina on 26/05/16.
 */
public class LoginResult {

    private boolean logado;
    private Optional<String> token;

    public LoginResult(boolean sucesso, Optional<String> token) {
        this.logado = sucesso;
        this.token = token;
    }

    public boolean isLogado() {
        return logado;
    }

    public Optional<String> getToken() {
        return token;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private boolean sucesso;
        private Optional<String> token;

        public Builder sucesso(boolean sucesso) {
            this.sucesso = sucesso;
            return this;
        }

        public Builder token(String token) {
            this.token = Optional.of(token);
            return this;
        }

        public LoginResult build() {
            return new LoginResult(sucesso, token);
        }
    }
}
