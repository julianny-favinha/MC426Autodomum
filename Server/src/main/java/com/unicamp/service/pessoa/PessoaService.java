package com.unicamp.service.pessoa;

import com.unicamp.dao.PessoaDao;
import com.unicamp.modelo.Pessoa;
import com.unicamp.seguranca.HashFunction;

import java.util.Optional;

/**
 * @author sabrina on 19/05/16.
 */
public class PessoaService {

    private final HashFunction hashFunction;
    private final PessoaDao pessoaDao;

    public PessoaService(HashFunction hashFunction, PessoaDao pessoaDao) {
        this.hashFunction = hashFunction;
        this.pessoaDao = pessoaDao;
    }

    public void cadastro(Pessoa pessoa) {
        pessoa.setSenha(hashFunction.apply(pessoa.getSenha()));
        pessoaDao.criar(pessoa);
    }

    public Optional<Pessoa> buscaPorEmail(String email) {
        return pessoaDao.buscaPorEmail(email);
    }

}
