package com.learning.recruiter.action.application;

import com.learning.recruiter.api.Application;
import com.learning.recruiter.mapper.ApplicationMapper;
import com.learning.recruiter.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Action to get single application
 */
@Component
public class GetApplicationAction {

    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private ApplicationRepository applicationRepository;

    /**
     * Method to get application by Id
     * @param applicationId
     * @return
     */
    public Application invoke(String applicationId) {
        return applicationMapper.mapFromEntity(applicationRepository.findOne(applicationId));
    }
}
