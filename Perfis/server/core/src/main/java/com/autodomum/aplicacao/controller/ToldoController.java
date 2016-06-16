package com.autodomum.aplicacao.controller;

import com.autodomum.comandos.ComandoToldo;
import com.autodomum.modelo.HistoricoToldo;
import com.autodomum.modelo.Toldo;
import com.autodomum.service.ToldoService;
import com.autodomum.service.usuario.results.DefaultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sabrina on 27/05/16.
 */
@RestController
@RequestMapping("/api/toldo")
public class ToldoController {

    @Autowired
    private ToldoService toldoService;

    @RequestMapping(value = "/historico", method = RequestMethod.GET)
    public List<HistoricoToldo> buscaHistorico(@RequestParam("toldo") Toldo toldo) {
        return toldoService.historicoToldo(toldo);
    }

    @RequestMapping(value = "/comando", method = RequestMethod.POST)
    public DefaultResponse comandarToldo(@RequestBody ComandoToldo comando) {
        toldoService.enviarComando(comando);
        return new DefaultResponse(true);
    }

}
