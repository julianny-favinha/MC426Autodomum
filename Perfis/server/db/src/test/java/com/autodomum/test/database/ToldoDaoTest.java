package com.autodomum.test.database;

import com.autodomum.dao.ToldoDao;
import com.autodomum.modelo.HistoricoToldo;
import com.autodomum.modelo.Toldo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author sabrina on 27/05/16.
 */
public class ToldoDaoTest {

    private ToldoDao toldoDao;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = PostgresTestSuite.jdbcTemplate();
        toldoDao = new ToldoDao(jdbcTemplate);
    }

    @After
    public void clean() {
        jdbcTemplate.execute("DELETE FROM historico_toldo;");
    }

    @Test
    public void criarHistorico() {
        HistoricoToldo esperado = new HistoricoToldo(true, LocalDateTime.now(), Toldo.VARAL);
        Integer id = toldoDao.criarHistorico(esperado);

        List<HistoricoToldo> historico = toldoDao.historico(Toldo.VARAL);
        assertThat(historico.size(), equalTo(1));
        assertThat(id, equalTo(id));
        assertHistoricoToldo(esperado, id, historico.get(0));
    }

    @Test
    public void historico() {
        HistoricoToldo esperado = new HistoricoToldo(true, LocalDateTime.now(), Toldo.VARAL);
        final Integer id = toldoDao.criarHistorico(esperado);
        HistoricoToldo esperado1 = new HistoricoToldo(true, LocalDateTime.now(), Toldo.VARAL);
        Integer id1 = toldoDao.criarHistorico(esperado1);
        HistoricoToldo esperado2 = new HistoricoToldo(true, LocalDateTime.now(), Toldo.JARDIM);
        toldoDao.criarHistorico(esperado2);

        List<HistoricoToldo> historico = toldoDao.historico(Toldo.VARAL);
        assertThat(historico.size(), equalTo(2));

        Optional<HistoricoToldo> obtido = historico.stream().filter(h -> h.getId() == id).findFirst();
        assertThat(obtido.isPresent(), is(true));
        assertHistoricoToldo(esperado, id, obtido.get());

        Optional<HistoricoToldo> obtido1 = historico.stream().filter(h -> h.getId() == id1).findFirst();
        assertThat(obtido1.isPresent(), is(true));
        assertHistoricoToldo(esperado, id1, obtido1.get());
    }

    @Test
    public void historicoVazio() {
        List<HistoricoToldo> historico = toldoDao.historico(Toldo.VARAL);
        assertThat(historico.size(), equalTo(0));
    }

    private void assertHistoricoToldo(HistoricoToldo esperado, Integer id, HistoricoToldo obtido) {
        assertThat(obtido.isFechado(), is(esperado.isFechado()));
        assertThat(obtido.getToldo(), equalTo(esperado.getToldo()));
    }


}
