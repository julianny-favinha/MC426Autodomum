package com.autodomum.dao.command.preferencia;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.autodomum.modelo.Preferencias;

public class CriarPreferenciaCommand implements Consumer<Preferencias>{

	private static String INSERT_PREFERENCIA =
            "INSERT INTO preferencias " +
                    "(username_usuario, artista)" +
                    " VALUES (:username, :artista)";
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public CriarPreferenciaCommand(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}
	public void accept(Preferencias preferencias) {
		Map<String, Object> prefParameters = new HashMap();
		prefParameters.put("username", preferencias.getNome());
		prefParameters.put("artista", preferencias.getArtista());
		
		jdbcTemplate.update(INSERT_PREFERENCIA, prefParameters);
	}

	
}
