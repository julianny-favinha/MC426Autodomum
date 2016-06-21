package com.autodomum.comandos;

import com.autodomum.modelo.Audio;

/**
 * @author igor :D on 20/06/16.
 */
public class ComandoAudio extends Comando{
	
	
	private String artista;
	private Audio audio;
	
    public ComandoAudio() {
        this.funcionalidade = Funcionalidade.AUDIO;
    }
    
    public String getArtista() {
        return artista;
    }
    
    public Audio getAudio() {
        return audio;
    }
    
    public void setAudio(Audio audio){
    	this.audio = audio; 
    }
    
    public void setArtista(String artista){
    	this.artista = artista;
    }
    

}
