package com.learning.recruiter.action.application;

import com.learning.recruiter.api.Application;
import com.learning.recruiter.api.ApplicationStatus;
import com.learning.recruiter.entity.ApplicationEntity;
import com.learning.recruiter.mapper.ApplicationMapper;
import com.learning.recruiter.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Action to create the application
 */
@Component
public class CreateApplicationAction {

    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private ApplicationRepository applicationRepository;

    /**
     * Action method to create application from DTO object
     * @param application
     * @return
     */
    public Application invoke(Application application) {
        application.setStatus(ApplicationStatus.APPLIED);
        ApplicationEntity applicationEntity =
                applicationRepository.createApplication(
                        applicationMapper.mapToEntity(application)
                );

        return applicationMapper.mapFromEntity(applicationEntity);
    }
}
