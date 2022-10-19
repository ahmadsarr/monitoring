package org.monitoring.config;

import org.monitoring.service.LoggerHttpRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@ComponentScan(basePackages = "org.monitoring.service")
public class AppConfiguration {
    @Value("${httpclient.request.connection_timeout}")
    private long connexionTimeout;
    @Value("${httpclient.request.read-timeout}")
    private long readTimeout;


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .interceptors(new LoggerHttpRequest())
                .setConnectTimeout(Duration.ofMillis(connexionTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }
}
