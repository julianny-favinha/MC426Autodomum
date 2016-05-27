package com.autodomum.aplicacao.config;

import com.autodomum.dao.ToldoDao;
import com.autodomum.dao.UsuarioDao;
import com.autodomum.service.AuthenticationService;
import com.autodomum.service.ToldoService;
import com.autodomum.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sabrina on 19/05/16.
 */
@Configuration
public class ServicesConfiguration {

    @Bean
    @Autowired
    AuthenticationService authenticationService(SecurityInfo securityInfo) {
        return new AuthenticationService(securityInfo);
    }

    @Bean
    @Autowired
    UsuarioService usuarioService(UsuarioDao usuarioDao, AuthenticationService authenticationService) {
        return new UsuarioService(usuarioDao, authenticationService);
    }

    @Bean
    @Autowired
    ToldoService toldoService(ToldoDao toldoDao) {
        return new ToldoService(toldoDao);
    }

}