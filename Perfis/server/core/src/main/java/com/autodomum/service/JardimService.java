package com.autodomum.service;

import com.autodomum.dao.HistoricoJardimDao;
import com.autodomum.modelo.HistoricoJardim;

import java.util.List;

/**
 * @author sabrina on 21/06/16.
 */
public class JardimService {

    private final HistoricoJardimDao historicoJardimDao;

    public JardimService(HistoricoJardimDao historicoJardimDao) {
        this.historicoJardimDao = historicoJardimDao;
    }

    public List<HistoricoJardim> historico() {
        return this.historicoJardimDao.historico();
    }

    public Integer criarHistorico(HistoricoJardim historicoJardim) {
        return historicoJardimDao.criarHistorico(historicoJardim);
    }

}
