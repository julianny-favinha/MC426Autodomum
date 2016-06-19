package com.autodomum.dao.command.usuario;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class BuscaPermissoesDeUsuarioCommand implements Function<String, List<Integer>> {

    private static final String SELECT_PERMISSOES_USUARIO =
            "SELECT id_permissao FROM usuario_permissoes "
                    + " WHERE username_usuario = :username";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public BuscaPermissoesDeUsuarioCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public List<Integer> apply(String username) {

        Map<String, Object> parameters = new HashMap();
        parameters.put("username", username);
        try {
            return jdbcTemplate.query(SELECT_PERMISSOES_USUARIO, parameters, (rs, i) -> rs.getInt("id_permissao"));
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }
}

