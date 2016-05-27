package com.autodomum.dao.usuario.command;

import com.autodomum.dao.usuario.UsuarioDao;
import com.autodomum.service.usuario.to.UsuarioTO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author sabrina on 16/05/16.
 */
public class BuscaUsuarioPorUsernameCommand implements Function<String, Optional<UsuarioTO>> {

    private static final String SELECT_USUARIO_POR_USERNAME = "SELECT * FROM usuario WHERE username = :username";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public BuscaUsuarioPorUsernameCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    public Optional<UsuarioTO> apply(String username) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("username", username);

        try {
            return Optional.of(jdbcTemplate.queryForObject(SELECT_USUARIO_POR_USERNAME, parameters, UsuarioDao.USUARIO_MAPPER));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
