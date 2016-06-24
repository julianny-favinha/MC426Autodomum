package com.autodomum.test.database;

import com.autodomum.dao.UsuarioDao;
import com.autodomum.modelo.Usuario;
import com.autodomum.service.usuario.to.UsuarioTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author sabrina on 26/05/16.
 */
public class UsuarioDaoTest {

    private UsuarioDao usuarioDao;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = PostgresTestSuite.jdbcTemplate();
        usuarioDao = new UsuarioDao(jdbcTemplate);
    }

    @After
    public void clean() {
        jdbcTemplate.execute("DELETE FROM usuario;");
    }

    @Test
    public void criar() {
        Usuario esperado = usuarioDeTest().build();
        usuarioDao.criar(esperado);

        Optional<UsuarioTO> foo = usuarioDao.buscaPorUsername(esperado.getUsername());
        assertThat(foo.isPresent(), is(true));
        assertUsuario(esperado, foo.get());
    }

    @Test
    public void buscaPorEmailQueNaoExiste() {
        Optional<UsuarioTO> foo = usuarioDao.buscaPorUsername("foobar");
        assertThat(foo.isPresent(), is(false));
    }

    @Test
    public void buscaSenha() {
        Usuario esperado = usuarioDeTest().build();
        usuarioDao.criar(esperado);
        Optional<String> senha = usuarioDao.buscaSenha(esperado.getUsername());

        assertThat(senha.isPresent(), is(true));
        assertThat(senha.get(), equalTo(esperado.getSenha()));
    }

    @Test
    public void buscaSenhaDeUsuarioQueNaoExiste() {
        Optional<String> senha = usuarioDao.buscaSenha("foobar");

        assertThat(senha.isPresent(), is(false));
    }

    @Test
    public void editar() {
        Usuario usuario = usuarioDeTest().build();
        usuarioDao.criar(usuario);

        UsuarioTO esperado = UsuarioTO.builder()
                .nome(usuario.getNome() + "1")
                .rfid(usuario.getRfid() + 5)
                .username(usuario.getUsername())
                .build();
        usuarioDao.editar(esperado);

        Optional<UsuarioTO> editado = usuarioDao.buscaPorUsername(esperado.getUsername());
        assertThat(editado.isPresent(), is(true));
        assertThat(editado.get().getUsername(), equalTo(esperado.getUsername()));
        assertThat(editado.get().getNome(), equalTo(esperado.getNome()));
        assertThat(editado.get().getRfid(), equalTo(esperado.getRfid()));
    }

    @Test
    public void listar() {
        Usuario usuario = usuarioDeTest().build();
        usuarioDao.criar(usuario);
        Usuario usuario1 = usuarioDeTest().username("foobar1").build();
        usuarioDao.criar(usuario1);
        Usuario usuario2 = usuarioDeTest().username("foobar2").build();
        usuarioDao.criar(usuario2);

        List<UsuarioTO> usuarios = usuarioDao.listar();
        assertThat(usuarios.size(), equalTo(3));
        assertUsuario(usuario, usuarios.get(0));
    }

    private void assertUsuario(Usuario esperado, UsuarioTO obtido) {
        assertThat(obtido.getUsername(), equalTo(esperado.getUsername()));
        assertThat(obtido.getNome(), equalTo(esperado.getNome()));
        assertThat(obtido.getRfid(), equalTo(esperado.getRfid()));
    }

    private Usuario.Builder usuarioDeTest() {
        return Usuario.builder()
                .nome("Foo")
                .senha("loucura")
                .rfid(84_116_97_235_170L)
                .username("foo");
    }


}
