package com.autodomum.aplicacao.controller;

import com.autodomum.modelo.Usuario;
import com.autodomum.modelo.UsuarioPermissoes;
import com.autodomum.service.usuario.results.DefaultResponse;
import com.autodomum.service.usuario.results.LoginResult;
import com.autodomum.service.AuthenticationService;
import com.autodomum.service.UsuarioService;
import com.autodomum.service.usuario.requests.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.lang.UsesUnsafeJava;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sabrina on 26/05/16.
 */
@RestController
@RequestMapping(value = "/api")
public class AuthenticationController {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public void novo(@RequestBody UsuarioPermissoes usuario) {
    	
    	List<Integer> permissoes = new ArrayList<Integer>();
		if (usuario.isNotificacoes()) {
			permissoes.add(1);
		}
		if (usuario.isJardim()) {
			permissoes.add(2);
		}
		if (usuario.isAudio()) {
			permissoes.add(3);
		}
		if (usuario.isVaral()) {
			permissoes.add(4);
		}
		
    	Usuario usuarioPersist  = new Usuario(usuario.getUsername(), 
    			usuario.getNome(), 
    			usuario.getSenha(), 
    			usuario.getRfid(),
    								   permissoes);
        usuarioService.cadastro(usuarioPersist);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public DefaultResponse login(HttpServletResponse response, @RequestBody LoginRequest loginData) {
        LoginResult result = usuarioService.login(loginData);

        if(result.isLogado()) {
            response.addCookie(new Cookie(AuthenticationService.AUTH_COOKIE, result.getToken().get()));
        }

        return new DefaultResponse(result.isLogado());
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie(AuthenticationService.AUTH_COOKIE, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
