package com.learning.recruiter.entity;

import com.learning.recruiter.api.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationEntity extends BaseEntity{

    private String candidateEmail;

    //Should be maintain as external file, link can be stored here
    private String resume;

    private ApplicationStatus status;

    private String offerId;
}
