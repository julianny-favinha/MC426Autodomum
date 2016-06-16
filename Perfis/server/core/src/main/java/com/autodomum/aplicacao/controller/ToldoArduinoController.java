package com.autodomum.aplicacao.controller;

import com.autodomum.modelo.HistoricoToldo;
import com.autodomum.service.ToldoService;
import com.autodomum.service.usuario.results.DefaultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sabrina on 16/06/16.
 */
@RestController
@RequestMapping("/toldo")
public class ToldoArduinoController {

    @Autowired
    private ToldoService toldoService;

    @RequestMapping(value = "/historico", method = RequestMethod.POST)
    public DefaultResponse criarHistorico(@RequestBody HistoricoToldo historicoToldo) {
        toldoService.criarHistorico(historicoToldo);
        return new DefaultResponse(true);
    }

}
