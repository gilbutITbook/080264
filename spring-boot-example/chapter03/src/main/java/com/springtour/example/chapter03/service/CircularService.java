package com.springtour.example.chapter03.service;

import org.springframework.stereotype.Service;

// @Service
// 원활한 예제 실행을 위해서 @Service 애너테이션을 주석처리합니다.
// 테스트를 위해서 애너테이션의 주석을 풀고 실행하기실 바랍니다.
public class CircularService {
    private CircularReference circularReference;

    public CircularService(CircularReference circularReference) {
        this.circularReference = circularReference;
    }
}
