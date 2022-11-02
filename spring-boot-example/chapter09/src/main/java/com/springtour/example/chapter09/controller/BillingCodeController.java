package com.springtour.example.chapter09.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class BillingCodeController {

    @PostMapping(path = "/billing-codes")
    public ApiResponse<CreateCodeResponse> createBillingCodes(@RequestBody CreateCodeRequest request) {

        return ApiResponse.ok(CreateCodeResponse.of(request.getIds()));
    }

    @GetMapping(path = "/billing-codes")
    public ApiResponse<List<BillingCodeResponse>> getBillingCodes(@RequestParam(required = false) String codeName) {
        List<BillingCodeResponse> responses = List.of(
                BillingCodeResponse.of("CODE-112123"),
                BillingCodeResponse.of("CODE-827125")
        );

        return ApiResponse.ok(responses);
    }

}
