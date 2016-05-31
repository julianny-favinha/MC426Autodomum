package com.autodomum.dao.command.permissao;

import com.autodomum.dao.PermissaoDao;
import com.autodomum.modelo.Permissao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author sabrina on 31/05/16.
 */
public class BuscarPermissoesCommand implements Supplier<List<Permissao>> {
    private static String SELECT_PERMISSOES = "SELECT id, nome, sistema FROM permissao";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public BuscarPermissoesCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public List<Permissao> get() {
        try {
            return jdbcTemplate.query(SELECT_PERMISSOES, PermissaoDao.PERMISSAO_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }
}
