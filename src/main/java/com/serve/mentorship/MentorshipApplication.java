package com.serve.mentorship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.management.ThreadDumpEndpointAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = { ThreadDumpEndpointAutoConfiguration.class })
public class MentorshipApplication {

    public static void main(String[] args) {
        SpringApplication.run(MentorshipApplication.class, args);
    }

}
