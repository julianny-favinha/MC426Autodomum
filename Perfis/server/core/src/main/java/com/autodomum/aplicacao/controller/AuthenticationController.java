package com.autodomum.aplicacao.controller;

import com.autodomum.service.usuario.AuthenticationService;
import com.autodomum.modelo.Usuario;
import com.autodomum.service.usuario.UsuarioService;
import com.autodomum.service.usuario.results.DefaultResponse;
import com.autodomum.service.usuario.results.LoginResult;
import com.autodomum.service.usuario.requests.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sabrina on 26/05/16.
 */
@RestController
@RequestMapping(value = "/")
public class AuthenticationController {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public void novo(@RequestBody Usuario usuario) {
        usuarioService.cadastro(usuario);
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
