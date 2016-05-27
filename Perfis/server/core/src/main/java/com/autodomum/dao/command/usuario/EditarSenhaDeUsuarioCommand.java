package com.autodomum.dao.command.usuario;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author sabrina on 26/05/16.
 */
public class EditarSenhaDeUsuarioCommand implements BiConsumer<String, String> {

    private static String UPDATE_SENHA_DE_USUARIO =
            "UPDATE usuario SET senha = :senha WHERE username = :username";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public EditarSenhaDeUsuarioCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void accept(String username, String novaSenha) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("senha", novaSenha);
        parameters.put("username", username);

        jdbcTemplate.update(UPDATE_SENHA_DE_USUARIO, parameters);
    }
}
