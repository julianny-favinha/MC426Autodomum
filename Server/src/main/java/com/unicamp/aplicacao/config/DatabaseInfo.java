package com.unicamp.aplicacao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author sabrina on 16/05/16.
 */
@Component
public class DatabaseInfo {

    @Value("${db.unicamp.jdbc.url}")
    private String jdbcUrl;

    @Value("${db.unicamp.maximum.pool.size}")
    private int maximumPoolSize;

    @Value("${db.unicamp.driver.class}")
    private String diverClass;

    @Value("${db.unicamp.connection.test.query}")
    private String connectionTestQuery;

    @Value("${db.unicamp.username}")
    private String username;

    @Value("${db.unicamp.password}")
    private String password;

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public String getDiverClass() {
        return diverClass;
    }

    public String getConnectionTestQuery() {
        return connectionTestQuery;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
