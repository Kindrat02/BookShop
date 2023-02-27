package com.serve.mentorship;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

import jakarta.annotation.PreDestroy;

@SpringBootApplication
public class MentorshipApplication {

    private static EmbeddedPostgres postgres;

    public static void main(String[] args) throws IOException {
        postgres = EmbeddedPostgres
                .builder()
                .setPort(5432)
                .start();

        SpringApplication.run(MentorshipApplication.class, args);
    }

    @PreDestroy
    public void closeDatabaseConnection() throws IOException {
        postgres.close();
    }
}
