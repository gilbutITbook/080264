package com.springtour.example.chapter03.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Getter
public class PriceUnit {

    private final Locale locale;

    public PriceUnit(Locale locale) {
        if (Objects.isNull(locale))
            throw new IllegalArgumentException("locale arg is null");

        this.locale = locale;
    }

    public String format(BigDecimal price) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        return currencyFormat.format(
                Optional.ofNullable(price).orElse(BigDecimal.ZERO)
        );
    }

    public void validate() {
        if (Objects.isNull(locale))
            throw new IllegalStateException("locale is null");

        log.info("locale is [{}]", locale);
    }
}
