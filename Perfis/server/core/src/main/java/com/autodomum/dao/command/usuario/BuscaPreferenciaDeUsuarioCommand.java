package com.autodomum.dao.command.usuario;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class BuscaPreferenciaDeUsuarioCommand implements Function<String, List<String>> {

    private static final String SELECT_PREFERENCIAS_USUARIO =
            "SELECT artista FROM preferencias "
                    + " WHERE username_usuario = :username";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public BuscaPreferenciaDeUsuarioCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public List<String> apply(String username) {

        Map<String, Object> parameters = new HashMap();
        parameters.put("username", username);
        try {
            return jdbcTemplate.query(SELECT_PREFERENCIAS_USUARIO, parameters, (rs, i) -> rs.getString("artista"));
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }
}

