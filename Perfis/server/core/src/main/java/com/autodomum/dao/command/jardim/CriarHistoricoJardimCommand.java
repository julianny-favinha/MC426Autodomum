package com.autodomum.dao.command.jardim;

import com.autodomum.modelo.HistoricoJardim;
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
 * @author sabrina on 21/06/16.
 */
public class CriarHistoricoJardimCommand implements Function<HistoricoJardim, Integer> {

    private static final String INSERT_HISTORICO_JARDIM =
            "INSERT INTO historico_jardim (temperatura, data, estado_chuva_id) " +
                    "VALUES (:temperatura, :data, :estado_chuva_id)";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public CriarHistoricoJardimCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public Integer apply(HistoricoJardim historicoJardim) {
        Date data = Date.from(historicoJardim.getData().atZone(ZoneId.systemDefault()).toInstant());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("temperatura", historicoJardim.getTemperatura());
        parameters.put("data", data);
        parameters.put("estado_chuva_id", historicoJardim.getEstadoChuva().getId());

        SqlParameterSource source = new MapSqlParameterSource(parameters);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT_HISTORICO_JARDIM, source, keyHolder, new String[] { "id" });
        return keyHolder.getKey().intValue();
    }
}
