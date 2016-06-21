package com.autodomum.aplicacao.config;

import com.autodomum.dao.PermissaoDao;
import com.autodomum.dao.PreferenciasDao;
import com.autodomum.dao.ToldoDao;
import com.autodomum.dao.UsuarioDao;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author sabrina on 16/05/16.
 */
@Configuration
public class DaosConfiguration {

    @Bean
    @Autowired
    public DataSource dataSource(DatabaseInfo dbConfig) {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(dbConfig.getDiverClass());
        dataSource.setJdbcUrl(dbConfig.getJdbcUrl());
        dataSource.setUsername(dbConfig.getUsername());
        dataSource.setPassword(dbConfig.getPassword());
        dataSource.setConnectionTestQuery(dbConfig.getConnectionTestQuery());
        dataSource.setMaximumPoolSize(dbConfig.getMaximumPoolSize());

        return dataSource;
    }

    @Bean
    @Autowired
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Autowired
    public UsuarioDao usuarioDao(JdbcTemplate jdbcTemplate) {
        return new UsuarioDao(jdbcTemplate);
    }

    @Bean
    @Autowired
    public ToldoDao toldoDao(JdbcTemplate jdbcTemplate) {
        return new ToldoDao(jdbcTemplate);
    }

    @Bean
    @Autowired
    public PermissaoDao permissaoDao(JdbcTemplate jdbcTemplate) {
        return new PermissaoDao(jdbcTemplate);
    }
    
    @Bean
    @Autowired
    public PreferenciasDao preferenciasDao(JdbcTemplate jdbcTemplate) {
    	return new PreferenciasDao(jdbcTemplate);
    }

}
