package com.learning.recruiter.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventType {
    APPLICATION_STATUS_CHANGED(1000);

    private Integer value;
}
