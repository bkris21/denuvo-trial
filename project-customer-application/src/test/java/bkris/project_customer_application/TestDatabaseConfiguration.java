package bkris.project_customer_application;


import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;

@TestConfiguration(proxyBeanMethods = false)
class TestDatabaseConfiguration {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
                .withDatabaseName("testDb")
                .withUsername("user")
                .withPassword("password");
    }

    @Bean
    public DataSource dataSource(PostgreSQLContainer<?> postgreSQLContainer) {
        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        pgSimpleDataSource.setDatabaseName(postgreSQLContainer.getDatabaseName());
        pgSimpleDataSource.setUrl(postgreSQLContainer.getJdbcUrl());
        pgSimpleDataSource.setUser(postgreSQLContainer.getUsername());
        pgSimpleDataSource.setPassword(postgreSQLContainer.getPassword());
        return pgSimpleDataSource;
    }

    @Bean
    public Flyway flyway(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .load();
    }
}