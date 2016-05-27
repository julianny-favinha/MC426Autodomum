package com.autodomum.dao;

import com.autodomum.dao.command.toldo.BuscaHistoricoToldoCommand;
import com.autodomum.dao.command.toldo.CriarHistoricoToldoCommand;
import com.autodomum.modelo.HistoricoToldo;
import com.autodomum.modelo.Toldo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * @author sabrina on 27/05/16.
 */
public class ToldoDao {

    public static final RowMapper<HistoricoToldo> TOLDO_MAPPER = (rs, i) ->
            new HistoricoToldo(rs.getInt("id"), rs.getBoolean("fechado"),
                    rs.getTimestamp("data").toLocalDateTime(), Toldo.buscaPorId(rs.getInt("toldo_id")));
    private JdbcTemplate jdbcTemplate;

    public ToldoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<HistoricoToldo> historico(Toldo toldo) {
        return new BuscaHistoricoToldoCommand(jdbcTemplate).apply(toldo);
    }

    public Integer criarHistorico(HistoricoToldo historicoToldo) {
        return new CriarHistoricoToldoCommand(jdbcTemplate).apply(historicoToldo);
    }
}
