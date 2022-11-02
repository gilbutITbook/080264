package com.springtour.example.chapter06.domain.email;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
public class EmailAddress {

    private static final String AT = "@";

    private String name;
    private String domainPart;
    private String localPart;

    public EmailAddress(String name, String localPart, String domainPart) {
        this.name = name;
        this.domainPart = domainPart;
        this.localPart = localPart;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (Objects.nonNull(name))
            sb.append(name).append(" ");

        return sb.append("<").append(localPart).append(AT).append(domainPart).append(">").toString();
    }
}
