package com.learning.recruiter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferEntity extends BaseEntity{

    private String title;

    private Date startDate;
}
