package com.autodomum.dao.usuario.command;

import com.autodomum.service.usuario.to.UsuarioTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author sabrina on 26/05/16.
 */
public class EditarUsuarioCommand implements Consumer<UsuarioTO> {

    private static String UPDATE_USUARIO =
            "UPDATE usuario " +
                    "SET nome = :nome, rfid = :rfid " +
                    "WHERE username = :username";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public EditarUsuarioCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void accept(UsuarioTO usuario) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("nome", usuario.getNome());
        parameters.put("rfid", usuario.getRfid());
        parameters.put("username", usuario.getUsername());

        jdbcTemplate.update(UPDATE_USUARIO, parameters);
    }
}
