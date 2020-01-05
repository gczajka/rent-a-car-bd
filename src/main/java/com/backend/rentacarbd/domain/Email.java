package com.backend.rentacarbd.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Email {
    private String mailTo;
    private String subject;
    private String message;
}
