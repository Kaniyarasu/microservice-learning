package com.learning.recruiter.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
    @JsonProperty("id")
    private String id;

    @JsonProperty("tle")
    private String title;

    @JsonProperty("s_dt")
    private Date startDate;

    @JsonProperty("no_as")
    private Integer numberOfApplicants;
}
