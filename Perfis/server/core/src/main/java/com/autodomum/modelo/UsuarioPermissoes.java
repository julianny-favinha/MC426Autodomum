package com.autodomum.modelo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
public class UsuarioPermissoes {

	private String username;
    private String nome;
    private String senha;
    private int rfid;
    private boolean notificacoes;
    private boolean jardim;
    private boolean varal;
    private boolean audio;
    
    
	public UsuarioPermissoes(String username, String nome, String senha, int rfid, boolean notificacoes, boolean jardim,
			boolean varal, boolean audio) {
		super();
		this.username = username;
		this.nome = nome;
		this.senha = senha;
		this.rfid = rfid;
		this.notificacoes = notificacoes;
		this.jardim = jardim;
		this.varal = varal;
		this.audio = audio;
	}
	
	public String getUsername() {
		return username;
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
	public boolean isNotificacoes() {
		return notificacoes;
	}
	public boolean isJardim() {
		return jardim;
	}
	public boolean isVaral() {
		return varal;
	}
	public boolean isAudio() {
		return audio;
	}
    
    
}
