package com.davidk1412.mypetdirectory.infrastructure.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    /**
     * Configures the DataSource bean using environment variables loaded by Dotenv.
     *
     * @return a DataSource instance configured with PostgreSQL settings.
     */
    @Bean
    public DataSource dataSource() {
        final Dotenv env = Dotenv.load();
        final String DB_URL = "jdbc:postgresql://" + env.get("DB_HOST") + ":" + env.get("DB_PORT") + "/" + env.get("DATABASE");
        System.out.println("Connecting to database at: " + DB_URL);

        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url(DB_URL)
                .username(env.get("DB_USER"))
                .password(env.get("DB_PASSWORD"))
                .build();
    }
}
