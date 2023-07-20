package com.legalease.model.response;

import com.legalease.model.CrossBorderCapability;
import lombok.Value;

@Value
public class FirmRegionDto {

    String id;

    RegionDto region;

    Boolean booking;

    CrossBorderCapability crossBorderCapability;

    Integer clientSatisfactionRating;

    Integer expertiseAndReputationRating;

}
