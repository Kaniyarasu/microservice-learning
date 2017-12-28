package com.learning.recruiter.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Holders for offer application status
 */
@Getter
@AllArgsConstructor
public enum ApplicationStatus {
    APPLIED(0),
    INVITED(1),
    REJECTED(2),
    HIRED(3);

    private Integer value;
}
