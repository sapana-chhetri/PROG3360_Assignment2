package com.example.product_service.config;

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
                .appName("product-service") // service name
                .instanceId(System.getenv().getOrDefault("HOSTNAME", "local"))
                .environment("development")
                .unleashAPI("http://localhost:4242/api")
                .apiKey(System.getenv().getOrDefault("UNLEASH_API_TOKEN", "default:development.6be178343bae8003f1fd5df78529a0d35ae6adc82e3ae3b27f9529c2"))
                .build();

        return new DefaultUnleash(config);
    }
}
