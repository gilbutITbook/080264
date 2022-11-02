package com.springtour.example.chapter03.domain.format;

public interface Formatter<T> {
    String of(T target);
}
