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
    private static String INSERT_USUARIO_PERMISSAO = 
    		"INSERT INTO usuario_permissoes"
    		+ "(username_usuario, id_permissao) "
    		+ "VALUES (:username, :id)";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public CriarUsuarioCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    public void accept(Usuario usuario) {
        Map<String, Object> userParameters = new HashMap();
        userParameters.put("nome", usuario.getNome());
        userParameters.put("senha", usuario.getSenha());
        userParameters.put("rfid", usuario.getRfid());
        userParameters.put("username", usuario.getUsername());

        jdbcTemplate.update(INSERT_USUARIO, userParameters);

        for (Integer i : usuario.getPermissoes()) {
        	Map<String, Object> permissionParameters = new HashMap();
        	permissionParameters.put("username", usuario.getUsername()); 
        	permissionParameters.put("id", i);
        	jdbcTemplate.update(INSERT_USUARIO_PERMISSAO, permissionParameters);
        }
    }
}
