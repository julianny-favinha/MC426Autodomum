package com.autodomum.test.database;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author sabrina on 26/05/16.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({UsuarioDaoTest.class, ToldoDaoTest.class})
public class PostgresTestSuite {

    private static EmbeddedPostgres pg;
    private static Connection conn;
    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate jdbcTemplate() {
        return jdbcTemplate;
    }

    @BeforeClass
    public static void setUp() throws IOException, SQLException {
        pg = EmbeddedPostgres.start();
        conn = pg.getPostgresDatabase().getConnection();
        jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(conn, false));

        Resource resource = new ClassPathResource("/db/migration/V1__initial_ddl.sql");
        ScriptUtils.executeSqlScript(conn, resource);
        resource = new ClassPathResource("/db/migration/V2__toldo.sql");
        ScriptUtils.executeSqlScript(conn, resource);
    }

    @AfterClass
    public static void tearDown() throws SQLException, IOException {
        conn.close();
        pg.close();
    }

}
