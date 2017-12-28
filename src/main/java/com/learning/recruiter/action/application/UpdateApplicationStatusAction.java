package com.learning.recruiter.action.application;

import com.learning.recruiter.api.Application;
import com.learning.recruiter.api.ApplicationStatus;
import com.learning.recruiter.api.EventType;
import com.learning.recruiter.helper.EventPublisher;
import com.learning.recruiter.mapper.ApplicationMapper;
import com.learning.recruiter.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Action to update application status
 */
@Component
public class UpdateApplicationStatusAction {

    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private ApplicationRepository applicationRepository;

    /**
     * Method to update the application status
     * @param applicationId
     * @param applicationStatus
     * @return Application
     */
    public Application invoke(String applicationId, ApplicationStatus applicationStatus) {
        Application application = applicationMapper.mapFromEntity(
                applicationRepository
                        .updateStatus(applicationId, applicationStatus));

        //Publishing application status change
        EventPublisher.publish(application.getId(), EventType.APPLICATION_STATUS_CHANGED);
        return application;
    }
}
