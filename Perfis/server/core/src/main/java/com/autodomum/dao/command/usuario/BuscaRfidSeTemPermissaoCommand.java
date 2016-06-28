package com.autodomum.dao.command.usuario;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * @author sabrina on 16/06/16.
 */
public class BuscaRfidSeTemPermissaoCommand implements BiFunction<Integer, Integer, Optional<Integer>> {

    private static final String SELECT_RFID =
            "SELECT usuario.rfid FROM usuario " +
            "JOIN usuario_permissoes up ON up.username_usuario = usuario.username " +
            "WHERE usuario.rfid = :rfid AND up.id_permissao = :permissaoId " +
            "LIMIT 1";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public BuscaRfidSeTemPermissaoCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public Optional<Integer> apply(Integer rfid, Integer permissaoId) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("rfid", rfid);
        parameters.put("permissaoId", permissaoId);

        try {
            return Optional.of(jdbcTemplate.queryForObject(SELECT_RFID, parameters,
                    (rs, i) -> rs.getInt("rfid")));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


}
