package com.springtour.example.chapter09.adapter;

import com.springtour.example.chapter09.controller.ApiResponse;
import com.springtour.example.chapter09.controller.BillingCodeResponse;
import com.springtour.example.chapter09.controller.CreateCodeRequest;
import com.springtour.example.chapter09.controller.CreateCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class BillingAdapter {

    private static final ParameterizedTypeReference<ApiResponse<CreateCodeResponse>> TYPE_REFERENCE;

    static {
        TYPE_REFERENCE = new ParameterizedTypeReference<>() {
        };
    }

    private final RestTemplate restTemplate;

    public BillingAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CreateCodeResponse createBillingCode(List<Long> hotelIds) {
        URI uri = UriComponentsBuilder.fromPath("/billing-codes")
                .scheme("http").host("127.0.0.1").port(8080)
                .build(false).encode()
                .toUri();

        CreateCodeRequest request = new CreateCodeRequest(1, hotelIds);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateCodeRequest> httpEntity = new HttpEntity<>(request, headers);

        ResponseEntity<ApiResponse<CreateCodeResponse>> responseEntity =
                restTemplate.exchange(uri, HttpMethod.POST, httpEntity, TYPE_REFERENCE);

        if (HttpStatus.OK != responseEntity.getStatusCode()) {
            log.error("Error from Billing. status:{}, hotelIds:{}", responseEntity.getStatusCode(), hotelIds);
            throw new RuntimeException("Error from Billing. " + responseEntity.getStatusCode());
        }

        return responseEntity.getBody().getData();
    }

    public CreateCodeResponse create(List<Long> hotelIds) {

        URI uri = UriComponentsBuilder.fromPath("/billing-codes")
                .scheme("http").host("127.0.0.1").port(8080)
                .build(false).encode()
                .toUri();

        CreateCodeRequest request = new CreateCodeRequest(1, hotelIds);

        ResponseEntity<ApiResponse> responseEntity =
                restTemplate.postForEntity(uri, request, ApiResponse.class);

        if (HttpStatus.OK != responseEntity.getStatusCode()) {
            log.error("Error from Billing. status:{}, hotelIds:{}", responseEntity.getStatusCode(), hotelIds);
            throw new RuntimeException("Error from Billing. " + responseEntity.getStatusCode());
        }

        ApiResponse apiResponse = responseEntity.getBody();
        Map<String, List<Long>> dataMap = (Map) apiResponse.getData();
        return CreateCodeResponse.of(dataMap.get("codes"));
    }

    public List<BillingCodeResponse> getBillingCodes(String codeNameParam) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/billing-codes")
                .scheme("http").host("127.0.0.1").port(8080);

        if (Objects.nonNull(codeNameParam))
            builder.queryParam("codeName", codeNameParam);

        URI uri = builder.build(false).encode().toUri();

        ResponseEntity<ApiResponse> responseEntity = restTemplate.getForEntity(uri, ApiResponse.class);
        if (HttpStatus.OK != responseEntity.getStatusCode()) {
            log.error("Error from Billing. status:{}, param:{}", responseEntity.getStatusCode(), codeNameParam);
            throw new RuntimeException("Error from Billing. " + responseEntity.getStatusCode());
        }

        ApiResponse apiResponse = responseEntity.getBody();
        return (List<BillingCodeResponse>) apiResponse.getData();
    }
}
