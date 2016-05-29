package com.autodomum.dao.command.usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.autodomum.service.usuario.to.UsuarioTO;

public class BuscaPermissoesPorUsuario implements Function<String, List<Integer>> {

    private static final String SELECT_PERMISSOES_USUARIO =
            "SELECT * " +
                    "FROM usuario_permissoes ";
//                    + "WHERE username_usuario = :username";

    private JdbcTemplate jdbcTemplate;

    public BuscaPermissoesPorUsuario(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Integer> apply(String username) {
    	
    	Map<String, Object> parameters = new HashMap();
        parameters.put("username", username);
        List<Integer> permissoes = new ArrayList<>();
        try {
        	List<Map<String,Object>> rows = jdbcTemplate.queryForList(SELECT_PERMISSOES_USUARIO);  
        	for (Map row : rows) {
                permissoes.add((Integer)(row.get("id_permissao")));
            }
        	       	
            return permissoes;
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }
}

