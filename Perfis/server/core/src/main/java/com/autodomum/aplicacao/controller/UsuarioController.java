package com.autodomum.aplicacao.controller;

import com.autodomum.service.usuario.UsuarioService;
import com.autodomum.service.usuario.requests.TrocarSenhaRequest;
import com.autodomum.service.usuario.results.DefaultResponse;
import com.autodomum.service.usuario.to.UsuarioTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sabrina on 16/05/16.
 */
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value = "/busca", method = RequestMethod.GET)
    public UsuarioTO buscar(@RequestParam("username") String username) {
        return usuarioService.buscaPorUsername(username)
                .orElse(null);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void editar(@RequestBody UsuarioTO usuario) {
        usuarioService.editar(usuario);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UsuarioTO> listar() {
        return usuarioService.listar();
    }

    @RequestMapping(value = "/trocarsenha", method = RequestMethod.PUT)
    public DefaultResponse trocarSenha(@RequestBody TrocarSenhaRequest request) {
        boolean trocou = usuarioService.trocarSenha(request);
        return new DefaultResponse(trocou);
    }

}
