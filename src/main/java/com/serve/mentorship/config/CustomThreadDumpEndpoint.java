package com.serve.mentorship.config;

import org.springframework.boot.actuate.management.ThreadDumpEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(
        value = "management.endpoints.web.exposure.exclude.threaddump",
        havingValue = "true")
@Configuration
public class CustomThreadDumpEndpoint {

    @Bean
    public ThreadDumpEndpoint dumpEndpoint() {
        return new ThreadDumpEndpoint();
    }
}
