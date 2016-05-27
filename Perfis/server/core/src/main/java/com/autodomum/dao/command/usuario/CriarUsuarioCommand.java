package com.autodomum.dao.command.usuario;


import com.autodomum.modelo.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author sabrina on 16/05/16.
 */
public class CriarUsuarioCommand implements Consumer<Usuario> {

    private static String INSERT_USUARIO =
            "INSERT INTO usuario " +
                    "(nome, senha, rfid, username)" +
                    " VALUES (:nome, :senha, :rfid, :username)";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public CriarUsuarioCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    public void accept(Usuario usuario) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("nome", usuario.getNome());
        parameters.put("senha", usuario.getSenha());
        parameters.put("rfid", usuario.getRfid());
        parameters.put("username", usuario.getUsername());

        jdbcTemplate.update(INSERT_USUARIO, parameters);
    }
}
