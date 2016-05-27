package com.autodomum.aplicacao.controller;

import com.autodomum.modelo.HistoricoToldo;
import com.autodomum.modelo.Toldo;
import com.autodomum.service.ToldoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sabrina on 27/05/16.
 */
@RestController
@RequestMapping("/api/toldo/historico")
public class ToldoController {

    @Autowired
    private ToldoService toldoService;

    @RequestMapping(method = RequestMethod.POST)
    public Integer criarHistorico(@RequestBody HistoricoToldo historicoToldo) {
        return toldoService.criarHistorico(historicoToldo);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<HistoricoToldo> buscaHistorico(@RequestParam("toldo") Toldo toldo) {
        return toldoService.historicoToldo(toldo);
    }

}
