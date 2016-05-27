package com.autodomum.service;

import com.autodomum.dao.ToldoDao;
import com.autodomum.modelo.HistoricoToldo;
import com.autodomum.modelo.Toldo;

import java.util.List;

/**
 * @author sabrina on 27/05/16.
 */
public class ToldoService {

    private ToldoDao toldoDao;

    public ToldoService(ToldoDao toldoDao) {
        this.toldoDao = toldoDao;
    }

    public List<HistoricoToldo> historicoToldo(Toldo toldo) {
        return this.toldoDao.historico(toldo);
    }

    public Integer criarHistorico(HistoricoToldo historicoToldo) {
        return toldoDao.criarHistorico(historicoToldo);
    }
}
