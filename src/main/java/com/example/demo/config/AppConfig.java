package com.example.demo.config;

import com.example.demo.model.Agent;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AppConfig {

    @Bean
    @ConfigurationProperties(prefix = "agent")
    public Agent getAgent(){
        return new Agent();
    }
}
