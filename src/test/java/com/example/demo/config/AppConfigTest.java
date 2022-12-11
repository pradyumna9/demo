package com.example.demo.config;

import com.example.demo.model.Agent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class AppConfigTest {

    private final ApplicationContextRunner applicationContextRunner = new ApplicationContextRunner();
    @Test
    void appServerConfiguredCorrectlyTest(){
        applicationContextRunner.withUserConfiguration(AppConfig.class)
                .withPropertyValues("app.server=s1")
                .run(context -> {
                    assertThat(context).hasBean("myServer");
                });
    }

    @Test
    void appServerNotConfiguredTest(){
        applicationContextRunner.withUserConfiguration(AppConfig.class)
                .run(context -> {
                    assertThat(context).doesNotHaveBean("myServer");
                });
    }

    @Test
    void agentPropertiesTest(){
        applicationContextRunner.withUserConfiguration(AppConfig.class)
                .withPropertyValues("agent.name=dipu")
                .run(context -> {
                    Agent agent = (Agent)context.getBean("baby");
                    assertThat(agent.getName()).isEqualTo("dipu");
                });
    }
}