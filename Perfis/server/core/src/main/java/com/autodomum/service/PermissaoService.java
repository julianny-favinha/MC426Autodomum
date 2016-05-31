package com.autodomum.service;

import com.autodomum.dao.PermissaoDao;
import com.autodomum.modelo.Permissao;

import java.util.List;

/**
 * @author sabrina on 31/05/16.
 */
public class PermissaoService {

    private final PermissaoDao permissaoDao;

    public PermissaoService(PermissaoDao permissaoDao) {
        this.permissaoDao = permissaoDao;
    }

    public List<Permissao> listarPermissoes() {
        return permissaoDao.permissoes();
    }

}
