package com.unicamp.aplicacao.config;

import com.unicamp.dao.PessoaDao;
import com.unicamp.seguranca.HashFunction;
import com.unicamp.service.pessoa.PessoaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sabrina on 19/05/16.
 */
@Configuration
public class ServicesConfiguration {

    @Bean
    HashFunction hashFunction() {
        return new HashFunction();
    }

    @Bean
    @Autowired
    PessoaService pessoaService(HashFunction hashFunction, PessoaDao pessoaDao) {
        return new PessoaService(hashFunction, pessoaDao);
    }

}
