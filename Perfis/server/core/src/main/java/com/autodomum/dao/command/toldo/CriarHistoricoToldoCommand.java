package com.autodomum.dao.command.toldo;

import com.autodomum.modelo.HistoricoToldo;
import org.springframework.cglib.core.internal.Function;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sabrina on 27/05/16.
 */
public class CriarHistoricoToldoCommand implements Function<HistoricoToldo, Integer> {

    private static final String INSERT_HISTORICO_TOLDO =
            "INSERT INTO historico_toldo (fechado, data, toldo_id) " +
                    "VALUES (:fechado, :data, :toldo_id)";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public CriarHistoricoToldoCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public Integer apply(HistoricoToldo historicoToldo) {
        Date data = Date.from(historicoToldo.getData().atZone(ZoneId.systemDefault()).toInstant());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fechado", historicoToldo.isFechado());
        parameters.put("data", data);
        parameters.put("toldo_id", historicoToldo.getToldo().getId());

        SqlParameterSource source = new MapSqlParameterSource(parameters);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT_HISTORICO_TOLDO, source, keyHolder, new String[] { "id" });
        return keyHolder.getKey().intValue();
    }
}
