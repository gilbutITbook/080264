package com.springtour.example.chapter09.adapter;

import com.springtour.example.chapter09.controller.ApiResponse;
import com.springtour.example.chapter09.controller.CreateCodeRequest;
import com.springtour.example.chapter09.controller.CreateCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Slf4j
@Component
public class WebClientBillingAdapter {

    private static final ParameterizedTypeReference<ApiResponse<CreateCodeResponse>> TYPE_REFERENCE;

    static {
        TYPE_REFERENCE = new ParameterizedTypeReference<>() {
        };
    }

    private final WebClient webClient;

    public WebClientBillingAdapter(WebClient billingWebClient) {
        this.webClient = billingWebClient;
    }

    public CreateCodeResponse createBillingCode(List<Long> hotelIds) {

        URI uri = UriComponentsBuilder.fromPath("/billing-codes")
                .scheme("http").host("127.0.0.1").port(8080)
                .build(false).encode()
                .toUri();
        CreateCodeRequest request = new CreateCodeRequest(1, hotelIds);

        return webClient.mutate()
                .build()
                .method(HttpMethod.POST).uri(uri)
                .bodyValue(request)
                .retrieve()
                .onStatus(httpStatus -> HttpStatus.OK != httpStatus,
                        response -> Mono.error(new RuntimeException("Error from Billing." + response.statusCode().value())))
                .bodyToMono(TYPE_REFERENCE)
                .flux().toStream()
                .findFirst()
                .map(ApiResponse::getData)
                .orElseThrow(() -> new RuntimeException("Empty response"));
    }
}
