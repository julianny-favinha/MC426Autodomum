package com.autodomum.dao.usuario.command;

import com.autodomum.dao.usuario.UsuarioDao;
import com.autodomum.service.usuario.to.UsuarioTO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author sabrina on 26/05/16.
 */
public class ListarUsuariosCommand implements Supplier<List<UsuarioTO>> {

    private static final String SELECT_USUARIOS =
            "SELECT nome, rfid, username " +
                    "FROM usuario";

    private JdbcTemplate jdbcTemplate;

    public ListarUsuariosCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UsuarioTO> get() {
        try {
            return jdbcTemplate.query(SELECT_USUARIOS, UsuarioDao.USUARIO_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }
}
