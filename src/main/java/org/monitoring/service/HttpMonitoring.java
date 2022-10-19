package org.monitoring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Component
@Slf4j
public class HttpMonitoring {
    private WebClient webClient;
    private List<String> servers ;

    public HttpMonitoring(WebClient webClient) {
        this.webClient = webClient;
        this.servers = List.of("http://localhost:8080","http://localhost:8081");
    }

    public boolean isServiceAvailable(String url) {
        System.out.println("iciciiic");
        try {
        webClient.get(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Scheduled(fixedRate = 1000)
    public void checkAllServices() {
        servers.forEach(server -> {
            if (isServiceAvailable(server)) {
                log.error(" {} is  available",server);
            } else {
                log.error(" {} is not available",server);
            }
        });
    }

}
