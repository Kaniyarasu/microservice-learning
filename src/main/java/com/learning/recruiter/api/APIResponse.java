package com.learning.recruiter.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class APIResponse<T>
{
    @JsonProperty("payload")
    private T payload;
}
