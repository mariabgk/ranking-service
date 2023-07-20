package com.legalease.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public abstract class AbstractContainerBaseTest {

    static final PostgreSQLContainer<?> POSTGRESQL_CONTAINER;
    private static final Log LOG = LogFactory.getLog(AbstractContainerBaseTest.class);
    private static final String POSTGRESQL_CONTAINER_DOCKER_IMAGE = "postgres:11.1";
    private static final int POSTGRESQL_CONTAINER_EXPOSED_PORT = 5432;

    static {
        POSTGRESQL_CONTAINER = new PostgreSQLContainer<>(POSTGRESQL_CONTAINER_DOCKER_IMAGE)
                .withDatabaseName("legalease_db")
                .withUsername("postgres")
                .withPassword("password")
                .withExposedPorts(POSTGRESQL_CONTAINER_EXPOSED_PORT)
                .withStartupTimeout(Duration.of(60, SECONDS))
                .withReuse(true);

        try {
            POSTGRESQL_CONTAINER.start();
        } catch (Exception e) {
            LOG.error("PostgreSQL TestContainer failed to start.", e);
            throw new TestContainerUnavailableException(String.format("PostgreSQL TestContainer failed to start. Reason: %s", e.getMessage()));
        }
    }

    @DynamicPropertySource
    static void datasourceConfig(DynamicPropertyRegistry registry) {
        registry.add("config.postgre.url", () -> POSTGRESQL_CONTAINER.getJdbcUrl() + "&stringtype=unspecified");
        registry.add("config.postgre.username", POSTGRESQL_CONTAINER::getUsername);
        registry.add("config.postgre.password", POSTGRESQL_CONTAINER::getPassword);
    }
}
