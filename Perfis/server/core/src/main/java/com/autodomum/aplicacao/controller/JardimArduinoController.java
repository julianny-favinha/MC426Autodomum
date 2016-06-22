package com.autodomum.aplicacao.controller;

import com.autodomum.modelo.HistoricoJardim;
import com.autodomum.service.JardimService;
import com.autodomum.service.usuario.results.DefaultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sabrina on 21/06/16.
 */

@RestController
@RequestMapping("/jardim")
public class JardimArduinoController {

    @Autowired
    private JardimService jardimService;

    @RequestMapping(value = "/historico", method = RequestMethod.POST)
    public DefaultResponse criarHistorico(@RequestBody HistoricoJardim historicoJardim) {
        jardimService.criarHistorico(historicoJardim);
        return new DefaultResponse(true);
    }

}
