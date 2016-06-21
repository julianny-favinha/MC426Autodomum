package com.autodomum.dao.command.usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class BuscaUsuarioPorRfidCommand implements Function<Integer , List<String>>  {
	
	 private static final String SELECT_USERNAME_RFID =
	            "SELECT usuario.username FROM usuario "
	            + "WHERE usuario.rfid = :rfid";

	    private NamedParameterJdbcTemplate jdbcTemplate;

	    public BuscaUsuarioPorRfidCommand(JdbcTemplate jdbcTemplate) {
	        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	    }

		@Override
		public List<String> apply(Integer rfid) {
			Map<String, Object> parameters = new HashMap();
			parameters.put("rfid", rfid);
			
			try {
				return jdbcTemplate.query(SELECT_USERNAME_RFID, parameters, (rs, i) -> rs.getString("username"));
		    } catch (EmptyResultDataAccessException e) {
		        return null;
		    }
			
		}
}
