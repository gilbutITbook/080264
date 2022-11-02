package com.springtour.example.chapter03.validator;

import java.util.Objects;

public class Validator {

    public static void notNull(Object object) {
        if (Objects.isNull(object))
            throw new IllegalArgumentException("argument is null");
    }

    public static void notEmpty(String str) {
        if (Objects.isNull(str) || str.trim().length() == 0)
            throw new IllegalArgumentException("argument is empty");
    }
}
