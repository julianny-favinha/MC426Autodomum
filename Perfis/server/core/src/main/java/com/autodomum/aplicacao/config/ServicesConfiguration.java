package com.autodomum.aplicacao.config;

import com.autodomum.aplicacao.queue.AutodomumQueue;
import com.autodomum.dao.*;
import com.autodomum.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    ToldoService toldoService(ToldoDao toldoDao, @Qualifier("casaQueue") AutodomumQueue queue) {
        return new ToldoService(toldoDao, queue);
    }

    @Bean
    @Autowired
    PermissaoService permissaoService(PermissaoDao permissaoDao) {
        return new PermissaoService(permissaoDao);
    }

    @Bean
    @Autowired
    AudioService audioService(PreferenciasDao preferenciasDao, @Qualifier("audioQueue") AutodomumQueue queue) {
        return new AudioService(preferenciasDao, queue);
    }

    @Bean
    @Autowired
    JardimService jardimService(HistoricoJardimDao historicoJardimDao) {
        return new JardimService(historicoJardimDao);
    }

}