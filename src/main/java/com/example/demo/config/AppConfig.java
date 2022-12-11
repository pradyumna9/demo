package com.example.demo.config;

import com.example.demo.model.Agent;
import com.example.demo.model.Server;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Data
@Configuration
@PropertySources
({@PropertySource(value = "classpath:agent/agent-details.yml",factory = YmlPropertySourceFactory.class )
        ,@PropertySource(value = "classpath:server.yml",factory = YmlPropertySourceFactory.class)
        })
public class AppConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "agent")
    public Agent getAgent(){
        return new Agent();
    }



    @Bean("baby")
    @ConfigurationProperties(prefix = "agent")
    public Agent babyAgent(){
        return new Agent();
    }

    @Bean("myServer")
    @ConfigurationProperties(prefix = "server")
    @ConditionalOnProperty(name = "app.server",havingValue = "s1")
    public Server getServer(){
        return new Server();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
