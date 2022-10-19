package org.monitoring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@AllArgsConstructor
public class WebClient {
    private RestTemplate restTemplate;

    public <T> T get(String url, Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }
    public <T> T get(String url, Class<T> responseType, Map<String,Object> uriVariables) {

        return restTemplate.getForObject(url, responseType,uriVariables);
    }
    public String get(String url) {
        return restTemplate.getForObject(url, String.class);
    }
}
