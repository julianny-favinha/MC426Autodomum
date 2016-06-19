package com.autodomum.dao.command.toldo;

import com.autodomum.dao.ToldoDao;
import com.autodomum.modelo.HistoricoToldo;
import com.autodomum.modelo.Toldo;
import org.springframework.cglib.core.internal.Function;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sabrina on 27/05/16.
 */
public class BuscaHistoricoToldoCommand implements Function<Toldo, List<HistoricoToldo>> {

    private static String SELECT_HISTORICO_TOLDO =
            "SELECT id, fechado, automatico, data, toldo_id FROM historico_toldo " +
                    "WHERE toldo_id = :toldoId " +
                    "ORDER BY data DESC " +
                    "LIMIT 5";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public BuscaHistoricoToldoCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public List<HistoricoToldo> apply(Toldo toldo) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("toldoId", toldo.getId());

        try {
            return jdbcTemplate.query(SELECT_HISTORICO_TOLDO, parameters, ToldoDao.TOLDO_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }
}
