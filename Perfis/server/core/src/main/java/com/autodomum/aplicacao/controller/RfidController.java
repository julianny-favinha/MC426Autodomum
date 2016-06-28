package com.autodomum.aplicacao.controller;

import com.autodomum.comandos.ComandoAudio;
import com.autodomum.modelo.Audio;
import com.autodomum.service.AudioService;
import com.autodomum.service.UsuarioService;
import com.autodomum.service.usuario.results.DefaultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sabrina on 16/06/16.
 */
@RestController
@RequestMapping("/usuario/rfid")
public class RfidController {

    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    AudioService audioService;

    @RequestMapping(method = RequestMethod.GET)
    public DefaultResponse autentica(@RequestParam("rfid") Long rfid) {
    	if (usuarioService.autenticaRfid(rfid) == true){
    		String username = usuarioService.getUserbyRfid(rfid);
    		ComandoAudio comando = new ComandoAudio();
    		String artista = audioService.buscaArtista(username);
    		comando.setAudio(Audio.PLAY);
    		comando.setArtista(artista);
    		audioService.enviarComando(comando);
    	}
        return new DefaultResponse(usuarioService.autenticaRfid(rfid));
    }

}
