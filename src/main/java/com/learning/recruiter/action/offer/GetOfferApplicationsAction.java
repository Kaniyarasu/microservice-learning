package com.learning.recruiter.action.offer;

import com.learning.recruiter.api.Application;
import com.learning.recruiter.mapper.ApplicationMapper;
import com.learning.recruiter.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Action to applications by offer
 */
@Component
public class GetOfferApplicationsAction {

    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private ApplicationRepository applicationRepository;

    /**
     * Method to get all applications by offer Id, pagination not supported
     * @param offerId
     * @return List<Application>
     */
    public List<Application> invoke(String offerId) {
        return applicationRepository.findOfferApplications(offerId)
                .stream()
                .map(applicationMapper::mapFromEntity)
                .collect(Collectors.toList());
    }
}
