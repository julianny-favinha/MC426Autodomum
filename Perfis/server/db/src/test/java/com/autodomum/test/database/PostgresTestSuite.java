package com.autodomum.test.database;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import ru.yandex.qatools.embed.postgresql.PostgresExecutable;
import ru.yandex.qatools.embed.postgresql.PostgresProcess;
import ru.yandex.qatools.embed.postgresql.PostgresStarter;
import ru.yandex.qatools.embed.postgresql.config.PostgresConfig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.String.format;

/**
 * @author sabrina on 26/05/16.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({UsuarioDaoTest.class})
public class PostgresTestSuite {

    private static PostgresProcess process;
    private static Connection conn;
    private static JdbcTemplate jdbcTemplate;
    private static Resource deleteScript;

    public static JdbcTemplate jdbcTemplate() {
        return jdbcTemplate;
    }

    @BeforeClass
    public static void setUp() throws IOException, SQLException {
        // starting Postgres
        PostgresStarter<PostgresExecutable, PostgresProcess> runtime = PostgresStarter.getDefaultInstance();
        final PostgresConfig config = PostgresConfig.defaultWithDbName("testCriar");
        PostgresExecutable exec = runtime.prepare(config);
        process = exec.start();

        // connecting to a running Postgres
        String url = format("jdbc:postgresql://%s:%s/%s",
                config.net().host(),
                config.net().port(),
                config.storage().dbName()
        );

        conn = DriverManager.getConnection(url);
        jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(conn, false));

        String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" + currentDir);

        Resource resource = new ClassPathResource("/db/migration/V1__initial_ddl.sql");
        ScriptUtils.executeSqlScript(conn, resource);

    }

    @AfterClass
    public static void tearDown() throws SQLException {
        // stopping Postgres
        conn.close();
        process.stop();
    }

}
