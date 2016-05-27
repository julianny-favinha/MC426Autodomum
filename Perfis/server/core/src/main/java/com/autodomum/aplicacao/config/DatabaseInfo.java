package com.autodomum.aplicacao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author sabrina on 16/05/16.
 */
@Component
public class DatabaseInfo {

    @Value("${db.autodomum.jdbc.url}")
    private String jdbcUrl;

    @Value("${db.autodomum.maximum.pool.size}")
    private int maximumPoolSize;

    @Value("${db.autodomum.driver.class}")
    private String diverClass;

    @Value("${db.autodomum.connection.test.query}")
    private String connectionTestQuery;

    @Value("${db.autodomum.username}")
    private String username;

    @Value("${db.autodomum.password}")
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
