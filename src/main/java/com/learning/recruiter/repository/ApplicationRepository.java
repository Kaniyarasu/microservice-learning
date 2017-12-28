package com.learning.recruiter.repository;

import com.learning.recruiter.api.ApplicationStatus;
import com.learning.recruiter.api.ErrorType;
import com.learning.recruiter.entity.ApplicationEntity;
import com.learning.recruiter.exception.DataNotExistsException;
import com.learning.recruiter.exception.InvalidDataException;
import com.learning.recruiter.util.Constant;
import com.learning.recruiter.util.Util;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ApplicationRepository holds the offer applicant data, and its index for searches.
 * Also provides API(CRUD) to access offer applicant data
 */
@Component
public class ApplicationRepository {
    private Map<String, ApplicationEntity> applications = new HashMap<>();

    private Set<String> offerUniqueApplicants = new HashSet<>();

    private Map<String, List<String>> offerApplicantIndex = new HashMap<>();

    /**
     * Validates and creates the application and indexes for search.
     * @param applicationEntity
     * @return ApplicationEntity
     */
    public ApplicationEntity createApplication(ApplicationEntity applicationEntity) {
        if(null != applicationEntity) {
            // Validates application
            validateApplication(applicationEntity);

            // Persists the applications
            String id = Util.generateId();
            applicationEntity.setId(id);
            applications.put(id, applicationEntity);

            // Create indexes and constraints data
            createIndexes(applicationEntity);
        }else {
            throw new InvalidDataException(ErrorType.APPLICATION_EMAIL_ALREADY_FOUND);
        }

        return applicationEntity;
    }

    /**
     * Find by application id
     * @param id
     * @return ApplicationEntity
     */
    public ApplicationEntity findOne(String id) {
        if(applications.containsKey(id)) {
            return applications.get(id);
        }

        throw new DataNotExistsException(ErrorType.ENTITY_NOT_FOUND, ApplicationEntity.class.getName());
    }

    /**
     * Find all applications for the given offer id
     * @param offerId
     * @return List<ApplicationEntity>
     */
    public List<ApplicationEntity> findOfferApplications(String offerId) {
        if(offerApplicantIndex.containsKey(offerId)) {
            List<String> applicationIds = offerApplicantIndex.get(offerId);

            return applicationIds
                    .stream()
                    .map(applications::get)
                    .collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    /**
     * Update status of the given application
     * @param id - Application Id, applicationStatus - New status
     * @return ApplicationEntity
     */
    public ApplicationEntity updateStatus(String id, ApplicationStatus applicationStatus) {
        ApplicationEntity applicationEntity = findOne(id);
        applicationEntity.setStatus(applicationStatus);
        return applicationEntity;
    }

    /**
     * Validates the given application for the following condition
     *   1) Unique candidate email for an offer
     * @param applicationEntity
     */
    private void validateApplication(ApplicationEntity applicationEntity)
    {
        // Validates unique candidate email
        if(offerUniqueApplicants.contains(getOfferApplicantKey(applicationEntity))) {
            throw new InvalidDataException(ErrorType.APPLICATION_EMAIL_ALREADY_FOUND);
        }
    }

    /**
     * Prepare key for offer applicatant email, which used in unique constraints
     * @param applicationEntity
     * @return String
     */
    private String getOfferApplicantKey(ApplicationEntity applicationEntity)
    {
        return applicationEntity.getOfferId()
                + Constant.SEPARATOR
                + Util.cleanString(applicationEntity.getCandidateEmail());
    }

    /**
     * Creates different search indexes and constraints
     * @param applicationEntity
     */
    private void createIndexes(ApplicationEntity applicationEntity)
    {
        offerUniqueApplicants.add(getOfferApplicantKey(applicationEntity));
        if(!offerApplicantIndex.containsKey(applicationEntity.getOfferId())) {
            offerApplicantIndex.put(applicationEntity.getOfferId(), new ArrayList<>());
        }

        List<String> applicationIds = offerApplicantIndex.get(applicationEntity.getOfferId());
        applicationIds.add(applicationEntity.getId());
    }
}
