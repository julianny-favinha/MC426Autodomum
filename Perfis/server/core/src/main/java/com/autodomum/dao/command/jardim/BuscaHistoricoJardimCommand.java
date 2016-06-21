package com.autodomum.dao.command.jardim;

import com.autodomum.dao.HistoricoJardimDao;
import com.autodomum.modelo.HistoricoJardim;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author sabrina on 21/06/16.
 */
public class BuscaHistoricoJardimCommand implements Supplier<List<HistoricoJardim>> {

    private static String SELECT_HISTORICO_JARDIM =
            "SELECT id, temperatura, data, estado_chuva_id FROM historico_jardim " +
                    "ORDER BY data DESC " +
                    "LIMIT 5";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public BuscaHistoricoJardimCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public List<HistoricoJardim> get() {
        try {
            return jdbcTemplate.query(SELECT_HISTORICO_JARDIM, HistoricoJardimDao.JARDIM_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }
}
