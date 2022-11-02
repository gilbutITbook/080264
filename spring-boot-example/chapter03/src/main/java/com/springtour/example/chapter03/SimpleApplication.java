package com.springtour.example.chapter03;

import com.springtour.example.chapter03.domain.PriceUnit;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Locale;

@Slf4j
public class SimpleApplication {

    public static void main(String[] args) {

        PriceUnit usPriceUnit = new PriceUnit(Locale.US);
        PriceUnit koreaPriceUnit = new PriceUnit(Locale.KOREA);

        log.info(usPriceUnit.format(BigDecimal.valueOf(1523.23)));
        log.info(koreaPriceUnit.format(BigDecimal.valueOf(1500)));
    }
}
