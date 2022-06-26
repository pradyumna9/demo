package com.example.demo.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Properties;

public class YmlPropertySourceFactory implements PropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        YamlPropertiesFactoryBean  yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(resource.getResource());
        Properties properties = yamlPropertiesFactoryBean.getObject();
        return new PropertiesPropertySource("props",properties);
//        YamlMapFactoryBean yamlMapFactoryBean = new YamlMapFactoryBean();
//        yamlMapFactoryBean.setResources(resource.getResource());
//        Map<String,Object> map = yamlMapFactoryBean.getObject();
//        return new MapPropertySource("maps",map);
    }
}
