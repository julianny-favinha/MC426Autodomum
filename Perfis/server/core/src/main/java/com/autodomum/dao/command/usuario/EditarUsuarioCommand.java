package com.autodomum.dao.command.usuario;

import com.autodomum.service.usuario.to.UsuarioTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private static String DELETE_PERMISSOES = "DELETE FROM usuario_permissoes " +
            "WHERE username_usuario = :username";

    private static String INSERT_USUARIO_PERMISSAO =
            "INSERT INTO usuario_permissoes"
                    + "(username_usuario, id_permissao) "
                    + "VALUES (:username, :id)";

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

        //TODO separar em commands diferente e corrigir para um jeito aceitável
        Map<String, Object> parametersRemovePermissoes = new HashMap<>();
        parametersRemovePermissoes.put("username", usuario.getUsername());
        jdbcTemplate.update(DELETE_PERMISSOES, parametersRemovePermissoes);


        Map<String, Object>[] permissoes = new Map[usuario.getPermissoes().size()];
        int count = 0;
        for (Integer id : usuario.getPermissoes()) {
            Map<String, Object> parametersPermissao = new HashMap<>();
            parametersPermissao.put("username", usuario.getUsername());
            parametersPermissao.put("id", id);
            permissoes[count] = parametersPermissao;
            count++;
        }
        jdbcTemplate.batchUpdate(INSERT_USUARIO_PERMISSAO, permissoes);
    }
}
