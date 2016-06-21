package com.autodomum.dao;

import com.autodomum.dao.command.jardim.BuscaHistoricoJardimCommand;
import com.autodomum.dao.command.jardim.CriarHistoricoJardimCommand;
import com.autodomum.dao.command.toldo.BuscaHistoricoToldoCommand;
import com.autodomum.dao.command.toldo.CriarHistoricoToldoCommand;
import com.autodomum.modelo.EstadoChuva;
import com.autodomum.modelo.HistoricoJardim;
import com.autodomum.modelo.HistoricoToldo;
import com.autodomum.modelo.Toldo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * @author sabrina on 21/06/16.
 */
public class HistoricoJardimDao {

    public static final RowMapper<HistoricoJardim> JARDIM_MAPPER = (rs, i) ->
            new HistoricoJardim(rs.getInt("id"), EstadoChuva.buscaPorId(rs.getInt("estado_chuva_id")), rs.getFloat("temperatura"),
                    rs.getTimestamp("data").toLocalDateTime());

    private JdbcTemplate jdbcTemplate;

    public HistoricoJardimDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<HistoricoJardim> historico() {
        return new BuscaHistoricoJardimCommand(jdbcTemplate).get();
    }

    public Integer criarHistorico(HistoricoJardim historicoJardim) {
        return new CriarHistoricoJardimCommand(jdbcTemplate).apply(historicoJardim);
    }
}
