package com.autodomum.aplicacao.controller;

import com.autodomum.modelo.HistoricoJardim;
import com.autodomum.service.JardimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sabrina on 21/06/16.
 */
@RestController
@RequestMapping("/api/jardim")
public class JardimController {

    @Autowired
    private JardimService jardimService;

    @RequestMapping(value = "/historico", method = RequestMethod.GET)
    public List<HistoricoJardim> buscaHistorico() {
        return jardimService.historico();
    }

}
