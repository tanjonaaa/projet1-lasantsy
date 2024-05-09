package org.lasantsy.app.repository.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DbConnector {
    private final DbProperties dbProperties;

    public DbConnector(DbProperties dbProperties) {
        this.dbProperties = dbProperties;
    }

    @Bean
    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                dbProperties.getUrl(),
                dbProperties.getUser(),
                dbProperties.getPassword()
        );

        connection.setAutoCommit(false);
        return connection;
    }
}
