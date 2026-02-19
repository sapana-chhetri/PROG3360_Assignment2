package com.example.order_service.config;

import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.util.UnleashConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UnleashConfiguration {

    @Bean
    public Unleash unleash() {
        // Create Unleash client using SDK UnleashConfig
        UnleashConfig config = UnleashConfig.builder()
                .appName("order-service") // your service name
                .instanceId(System.getenv().getOrDefault("HOSTNAME", "local"))
                .environment("development")
                .unleashAPI("http://unleash-server:4242/api")
                .apiKey(System.getenv().getOrDefault("UNLEASH_API_TOKEN", "default-token"))
                .build();

        return new DefaultUnleash(config);
    }
}