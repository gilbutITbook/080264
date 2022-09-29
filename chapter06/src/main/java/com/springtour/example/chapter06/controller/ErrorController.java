package com.springtour.example.chapter06.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Locale;

@Slf4j
@RestController
public class ErrorController {

    private MessageSource messageSource;

    public ErrorController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping(path = "/error")
    public void createError() {

        System.out.println("---------------------------------");

        Locale locale = LocaleContextHolder.getLocale();
        String[] args = {"10"};
        String errorMessage = messageSource.getMessage("main.cart.tooltip", args, locale);
        BadRequestException badRequestException = new BadRequestException(errorMessage);

        LocalDate errorDate = LocalDate.now();
        log.trace("trace log at, {}", errorDate);
        log.debug("debug log at, {}", errorDate);
        log.info("info log at, {}", errorDate);
        log.warn("warn log at, {}", errorDate);
        log.error("error log at, {}, {}", errorDate, "errorMessage", badRequestException);

        throw badRequestException;
    }
}
