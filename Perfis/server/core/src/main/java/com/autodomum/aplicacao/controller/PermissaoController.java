package com.autodomum.aplicacao.controller;

import com.autodomum.modelo.Permissao;
import com.autodomum.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sabrina on 31/05/16.
 */
@RestController
@RequestMapping("/api/permissao")
public class PermissaoController {

    @Autowired
    private PermissaoService permissaoService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Permissao> listarPermissoes() {
        return permissaoService.listarPermissoes();
    }

}
