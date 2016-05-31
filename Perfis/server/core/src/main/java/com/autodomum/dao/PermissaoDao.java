package com.autodomum.dao;

import com.autodomum.dao.command.permissao.BuscarPermissoesCommand;
import com.autodomum.modelo.Permissao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * @author sabrina on 31/05/16.
 */
public class PermissaoDao {
    public static final RowMapper<Permissao> PERMISSAO_MAPPER = (rs, i) ->
            new Permissao(rs.getInt("id"), rs.getString("nome"), rs.getString("sistema"));

    private final JdbcTemplate jdbcTemplate;

    public PermissaoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Permissao> permissoes() {
        return new BuscarPermissoesCommand(jdbcTemplate).get();
    }
}
