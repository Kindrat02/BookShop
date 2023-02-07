package com.serve.mentorship.config;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

import javax.sql.DataSource;

// TODO: Create a separate schema on server
//  https://stackoverflow.com/questions/53775763/opentable-embedded-postgresql-not-working

@Configuration
public class EmbeddedDB {

    @Bean
    public DataSource embeddedPostgres() throws IOException {
        return EmbeddedPostgres.builder().start().getPostgresDatabase();
    }
}