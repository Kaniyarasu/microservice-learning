package com.learning.recruiter.mapper;

import com.learning.recruiter.api.Application;
import com.learning.recruiter.entity.ApplicationEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper between Application and ApplicationEntity
 */
@Component
public class ApplicationMapper extends BaseMapper<Application, ApplicationEntity> {
    public ApplicationMapper() {
        super(Application.class, ApplicationEntity.class);
    }
}