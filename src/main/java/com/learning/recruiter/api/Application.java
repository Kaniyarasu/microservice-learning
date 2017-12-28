package com.learning.recruiter.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @JsonProperty("id")
    private String id;

    @JsonProperty("c_el")
    private String candidateEmail;

    //Should be maintain as external file(S3, DFS), link can be stored here. Right now resume text is stored here
    @JsonProperty("res")
    private String resume;

    @JsonProperty("sts")
    private ApplicationStatus status;

    @JsonProperty("o_id")
    private String offerId;
}
