package com.autodomum.modelo;

public class Preferencias {

	private int id;
	private String artista;
	private String nome;
	
	public Preferencias(){
	}
	
	public Preferencias(int id, String nome, String artista) {
		this.setId(id);
		this.setNome(nome);
		this.setArtista(artista);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
