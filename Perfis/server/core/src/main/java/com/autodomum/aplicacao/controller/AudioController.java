package com.autodomum.aplicacao.controller;

import com.autodomum.comandos.ComandoAudio;
import com.autodomum.modelo.Preferencias;
import com.autodomum.service.AudioService;
import com.autodomum.service.usuario.results.DefaultResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sabrina on 27/05/16.
 */
@RestController
@RequestMapping("/api/audio")
public class AudioController {

    @Autowired
    private AudioService audioService;

    @RequestMapping(value = "/comando", method = RequestMethod.POST)
    public DefaultResponse comandarAudio(@RequestBody ComandoAudio command) {
        audioService.enviarComando(command);
        return new DefaultResponse(true);
    }
    
    @RequestMapping(value = "/artista", method = RequestMethod.GET)
    public String buscaArtista(@RequestParam("username") String username){
    	String artista = audioService.BuscaArtista(username);
    	if (artista != "0")
    		return "{\"artista\":\""+artista+"\"}";
    	return "{\"artista\":\"acdc\"}";
    }
    
    @RequestMapping(value = "/artista/new", method = RequestMethod.POST)
    public void novoArtista(@RequestBody Preferencias preferencia){
    	audioService.cadastroArtista(preferencia);
    }
    

}
