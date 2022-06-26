package com.example.demo.config;

import com.example.demo.model.Agent;
import com.example.demo.model.Server;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Data
@Configuration
@PropertySources
({@PropertySource(value = "classpath:agent/agent-details.yml",factory = YmlPropertySourceFactory.class )
        ,@PropertySource(value = "classpath:server.yml",factory = YmlPropertySourceFactory.class )})
public class AppConfig {

    @Bean
    @ConfigurationProperties(prefix = "agent")
    public Agent getAgent(){
        return new Agent();
    }

    @Bean
    @ConfigurationProperties(prefix = "server")
    public Server getServer(){
        return new Server();
    }
}
