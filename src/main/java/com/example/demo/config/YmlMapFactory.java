package com.example.demo.config;

import org.springframework.beans.factory.config.MapFactoryBean;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Map;

public class YmlMapFactory implements PropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        YamlMapFactoryBean yamlMapFactoryBean = new YamlMapFactoryBean();
        yamlMapFactoryBean.setResources(resource.getResource());
        Map<String,Object> map = yamlMapFactoryBean.getObject();
        return new MapPropertySource("maps",map);
    }
}
