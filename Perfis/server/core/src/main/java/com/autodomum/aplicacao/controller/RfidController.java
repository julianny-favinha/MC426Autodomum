package com.autodomum.aplicacao.controller;

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

    @RequestMapping(method = RequestMethod.GET)
    public DefaultResponse autentica(@RequestParam("rfid") int rfid) {
        return new DefaultResponse(usuarioService.autenticaRfid(rfid));
    }

}
