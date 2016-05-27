package com.autodomum.dao;

import com.autodomum.dao.command.usuario.*;
import com.autodomum.modelo.Usuario;
import com.autodomum.service.usuario.to.UsuarioTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

/**
 * @author sabrina on 16/05/16.
 */
public class UsuarioDao {

    public static final RowMapper<UsuarioTO> USUARIO_MAPPER = (rs, i) ->
            UsuarioTO.builder()
                    .nome(rs.getString("nome"))
                    .rfid(rs.getInt("rfid"))
                    .username(rs.getString("username"))
                    .build();

    private JdbcTemplate jdbcTemplate;

    public UsuarioDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void criar(Usuario usuario) {
        new CriarUsuarioCommand(jdbcTemplate).accept(usuario);
    }

    public Optional<UsuarioTO> buscaPorUsername(String username) {
        return new BuscaUsuarioPorUsernameCommand(jdbcTemplate).apply(username);
    }

    public Optional<String> buscaSenha(String username) {
        return new BuscaSenhaDeUsuarioPorUsername(jdbcTemplate).apply(username);
    }

    public void editar(UsuarioTO usuario) {
        new EditarUsuarioCommand(jdbcTemplate).accept(usuario);
    }


    public List<UsuarioTO> listar() {
        return new ListarUsuariosCommand(jdbcTemplate).get();
    }

    public void editarSenha(String username, String novaSenha) {
        new EditarSenhaDeUsuarioCommand(jdbcTemplate).accept(username, novaSenha);
    }
}
