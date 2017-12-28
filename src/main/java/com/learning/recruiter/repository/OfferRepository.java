package com.learning.recruiter.repository;

import com.learning.recruiter.api.ErrorType;
import com.learning.recruiter.exception.DataNotExistsException;
import com.learning.recruiter.exception.InvalidDataException;
import com.learning.recruiter.util.Util;
import com.learning.recruiter.entity.OfferEntity;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * OfferRepository holds the offer data, and its index for searches.
 * Also provides API(CRUD) to access offer data
 */
@Component
public class OfferRepository {
    private Map<String, OfferEntity> offers = new HashMap<>();

    private Set<String> titleUniqueConstraints = new HashSet<>();

    /**
     * Validates and persists the offers. Also creates respective index
     * @param offerEntity - OfferEntity
     * @return OfferEntity - After persistence(in memory)
     */
    public OfferEntity createOffer(OfferEntity offerEntity) {
        if(null != offerEntity) {
            //Validates offer
            validateOffer(offerEntity);

            //Persists offer
            String newId = Util.generateId();
            offerEntity.setId(newId);
            offers.put(newId, offerEntity);

            //Create Indexes
            titleUniqueConstraints.add(Util.cleanString(offerEntity.getTitle()));
        } else {
            throw new InvalidDataException(ErrorType.OFFER_INVALID);
        }

        return offerEntity;
    }

    public OfferEntity findOne(String id) {
        if(offers.containsKey(id)) {
            return offers.get(id);
        }

        throw new DataNotExistsException(ErrorType.ENTITY_NOT_FOUND, OfferEntity.class.getName());
    }

    public Collection<OfferEntity> findAll() {
        return offers.values();
    }

    public void deleteAll() {
        offers.keySet().removeAll(offers.keySet());
    }

    /**
     * Validates for the following conditions
     *  1) Unique title
     * @param offerEntity - OfferEntity
     */
    private void validateOffer(OfferEntity offerEntity)
    {
        //Validates the unique title
        if(titleUniqueConstraints.contains(Util.cleanString(offerEntity.getTitle()))) {
            throw new InvalidDataException(ErrorType.OFFER_ALREADY_EXIST, offerEntity.getTitle());
        }
    }
}
