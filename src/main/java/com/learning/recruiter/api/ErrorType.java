package com.learning.recruiter.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

    // Offer related error codes
    OFFER_ALREADY_EXIST("EC_1001"),
    OFFER_INVALID("EC_1002"),

    // Application related error codes
    APPLICATION_INVALID("EC_2001"),
    APPLICATION_EMAIL_ALREADY_FOUND("EC_2002"),

    // Common error codes
    ENTITY_NOT_FOUND("EC_3001");

    private String value;
}
