package com.autodomum.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.autodomum.dao.command.preferencia.CriarPreferenciaCommand;
import com.autodomum.dao.command.usuario.BuscaPreferenciaDeUsuarioCommand;
import com.autodomum.modelo.Preferencias;

public class PreferenciasDao {
	 public static final RowMapper<Preferencias> PREFERENCIAS_MAPPER = (rs, i) -> 
	 	new Preferencias(rs.getInt("id"), rs.getString("username"), rs.getString("artista"));

     private JdbcTemplate jdbcTemplate;
     
     public PreferenciasDao(JdbcTemplate jdbcTemplate) {
         this.jdbcTemplate = jdbcTemplate;
     }
     
     public List<String> BuscaDeArtistas(String username) {
         return new BuscaPreferenciaDeUsuarioCommand(jdbcTemplate).apply(username);
     }
     
     public void criar(Preferencias preferencia){
     	new CriarPreferenciaCommand(jdbcTemplate).accept(preferencia);
     }
}
