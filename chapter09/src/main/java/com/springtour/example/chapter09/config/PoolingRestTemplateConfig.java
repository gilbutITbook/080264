package com.springtour.example.chapter09.config;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@Configuration
public class PoolingRestTemplateConfig {

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {

        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(100);
        manager.setDefaultMaxPerRoute(5);

        HttpHost httpHost = new HttpHost("10.192.10.111", 8080, "http");
        manager.setMaxPerRoute(new HttpRoute(httpHost), 10);

        return manager;
    }

    @Bean
    public RequestConfig requestConfig() {
        return RequestConfig.custom()
                .setConnectionRequestTimeout(3000)
                .setConnectTimeout(3000)
                .setSocketTimeout(1000)
                .build();
    }

    @Bean
    public CloseableHttpClient httpClient() {
        return HttpClientBuilder.create()
                .setConnectionManager(poolingHttpClientConnectionManager())
                .setDefaultRequestConfig(requestConfig())
                .build();
    }

    @Bean
    public RestTemplate poolingRestTemplate() {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient());
        return new RestTemplate(requestFactory);
    }
}


