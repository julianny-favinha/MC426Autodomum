package com.autodomum.service.usuario.to;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sabrina on 26/05/16.
 */
public class UsuarioTO {

    private String username;
    private String nome;
    private Long rfid;
    private List<Integer> permissoes;

    public UsuarioTO() {
        permissoes = new ArrayList<>();
    }

    public UsuarioTO(String username, String nome, Long rfid) {
        this();
        this.username = username;
        this.nome = nome;
        this.rfid = rfid;
    }

    public String getNome() {
        return nome;
    }

    public String getUsername() {
        return username;
    }

    public Long getRfid() {
        return rfid;
    }

    public List<Integer> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Integer> permissoes) {
		this.permissoes = permissoes;
	}

	public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;
        private String nome;
        private Long rfid;
        private List<Integer> permissoes;

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder permissoes(List<Integer> permissoes) {
        	 this.permissoes = permissoes;
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

        public UsuarioTO build() {
            return new UsuarioTO(username, nome, rfid);
        }
    }
}
