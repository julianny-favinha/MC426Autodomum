package com.autodomum.aplicacao.config;

import com.autodomum.dao.usuario.UsuarioDao;
import com.autodomum.service.usuario.AuthenticationService;
import com.autodomum.service.usuario.UsuarioService;
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

}