package org.linlinjava.litemall.core.express.config;

import org.linlinjava.litemall.core.express.SfExpressService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SfExpressProperties.class)
public class SfExpressAutoConfiguration {

    private final SfExpressProperties properties;

    public SfExpressAutoConfiguration(SfExpressProperties properties) {
        this.properties = properties;
    }

    @Bean
    public SfExpressService expressService() {
        SfExpressService expressService = new SfExpressService();
        expressService.setProperties(properties);
        return expressService;
    }

}
