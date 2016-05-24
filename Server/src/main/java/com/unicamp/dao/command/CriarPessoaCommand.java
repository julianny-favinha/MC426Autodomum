package com.unicamp.dao.command;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.unicamp.modelo.Pessoa;

import java.sql.Date;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author sabrina on 16/05/16.
 */
public class CriarPessoaCommand implements Consumer<Pessoa> {

    private static String INSERT_PESSOA =
            "INSERT INTO pessoa " +
                    "(email, senha, nome, sobrenome, ddi_celular, ddd_celular, numero_celular, data_de_nascimento)" +
                    " VALUES (:email, :senha, :nome, :sobrenome, :ddi_celular, :ddd_celular, :numero_celular, :data_de_nascimento)";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public CriarPessoaCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    public void accept(Pessoa pessoa) {
        java.util.Date dataDeNascimento = Date.from(pessoa.getDataDeNascimento().atStartOfDay(ZoneId.systemDefault()).toInstant());

        Map<String, Object> parameters = new HashMap();
        parameters.put("email", pessoa.getEmail());
        parameters.put("senha", pessoa.getSenha());
        parameters.put("nome", pessoa.getNome());
        parameters.put("sobrenome", pessoa.getSobrenome());
        parameters.put("ddi_celular", pessoa.getCelular().getDdi());
        parameters.put("ddd_celular", pessoa.getCelular().getDdd());
        parameters.put("numero_celular", pessoa.getCelular().getNumero());
        parameters.put("data_de_nascimento", dataDeNascimento);

        jdbcTemplate.update(INSERT_PESSOA, parameters);
    }
}
