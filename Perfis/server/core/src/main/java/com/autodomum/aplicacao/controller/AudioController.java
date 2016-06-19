package com.autodomum.aplicacao.controller;

import com.autodomum.aplicacao.queue.AutodomumQueue;
import com.autodomum.comandos.Comando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author sabrina on 19/06/16.
 */
@RestController
@RequestMapping("audio/comando")
public class AudioController {

    @Autowired
    @Qualifier("audioQueue")
    AutodomumQueue queue;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Collection<Comando> lerComandos() {
        return queue.receiveAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Comando lerComando() {
        return queue.receive();
    }

}
