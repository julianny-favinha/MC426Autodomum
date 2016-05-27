package com.autodomum.dao.usuario.command;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author sabrina on 26/05/16.
 */
public class BuscaSenhaDeUsuarioPorUsername implements Function<String, Optional<String>> {

    private static final String SELECT_SENHA_DE_USUARIO_POR_EMAIL =
            "SELECT senha FROM usuario WHERE username = :username";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public BuscaSenhaDeUsuarioPorUsername(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public Optional<String> apply(String username) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("username", username);

        try {
            return Optional.of(jdbcTemplate.queryForObject(SELECT_SENHA_DE_USUARIO_POR_EMAIL, parameters,
                    (rs, i) -> rs.getString("senha")));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}

