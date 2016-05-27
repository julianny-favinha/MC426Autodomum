package com.autodomum.service;

import com.autodomum.dao.UsuarioDao;
import com.autodomum.modelo.Usuario;
import com.autodomum.service.usuario.requests.LoginRequest;
import com.autodomum.service.usuario.requests.TrocarSenhaRequest;
import com.autodomum.service.usuario.results.LoginResult;
import com.autodomum.service.usuario.to.UsuarioTO;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

/**
 * @author sabrina on 19/05/16.
 */
public class UsuarioService {

    private final UsuarioDao usuarioDao;
    private final AuthenticationService authenticator;

    public UsuarioService(UsuarioDao usuarioDao, AuthenticationService authenticator) {
        this.usuarioDao = usuarioDao;
        this.authenticator = authenticator;
    }

    public void cadastro(Usuario usuario) {
        String hashedPwd = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
        usuario.setSenha(hashedPwd);
        usuarioDao.criar(usuario);
    }

    public Optional<UsuarioTO> buscaPorUsername(String username) {
        return usuarioDao.buscaPorUsername(username);
    }

    public LoginResult login(LoginRequest loginData) {
        if (!validarSenha(loginData.getUsername(), loginData.getSenha())) {
            return LoginResult.builder().sucesso(false).build();
        }

        String token = authenticator.generateToken(loginData.getUsername());

        return LoginResult.builder().sucesso(true).token(token).build();
    }

    public void editar(UsuarioTO usuario) {
        usuarioDao.editar(usuario);
    }

    public List<UsuarioTO> listar() {
        return usuarioDao.listar();
    }

    public boolean trocarSenha(TrocarSenhaRequest request) {
        if(!validarSenha(request.getUsername(), request.getSenhaAntiga())) {
            return false;
        }

        String hashedPwd = BCrypt.hashpw(request.getNovaSenha(), BCrypt.gensalt());
        usuarioDao.editarSenha(request.getUsername(), hashedPwd);
        return true;
    }

    private boolean validarSenha(String username, String senha) {
        Optional<String> hashedPwd = usuarioDao.buscaSenha(username);

        if (!hashedPwd.isPresent()) {
            return false;
        }

        if (!BCrypt.checkpw(senha, hashedPwd.get())) {
            return false;
        }

        return true;
    }
}
