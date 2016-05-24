package com.unicamp.dao.command;

import com.unicamp.modelo.Celular;
import com.unicamp.modelo.Pessoa;
import com.unicamp.modelo.builder.PessoaBuilder;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author sabrina on 16/05/16.
 */
public class BuscaPessoaPorEmailCommand implements Function<String, Optional<Pessoa>> {

    private static final String SELECT_PESSOA_POR_EMAIL = "SELECT * FROM pessoa WHERE email = :email";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public BuscaPessoaPorEmailCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    public Optional<Pessoa> apply(String email) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("email", email);

        try {
            return Optional.of(jdbcTemplate.queryForObject(SELECT_PESSOA_POR_EMAIL, parameters, new RowMapper<Pessoa>() {
                public Pessoa mapRow(ResultSet rs, int i) throws SQLException {
                    return new PessoaBuilder()
                            .email(rs.getString("email"))
                            .senha(rs.getString("senha"))
                            .nome(rs.getString("nome"))
                            .sobrenome(rs.getString("sobrenome"))
                            .celular(new Celular(rs.getInt("ddi_celular"), rs.getInt("ddd_celular"), rs.getInt("numero_celular")))
                            .dataDeNascimento(rs.getTimestamp("data_de_nascimento").toLocalDateTime().toLocalDate())
                            .build();
                }
            }));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
