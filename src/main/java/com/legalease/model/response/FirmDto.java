package com.legalease.model.response;


import lombok.Value;

import java.util.List;
import java.util.Set;

@Value
public class FirmDto {

    Integer id;
    String name;

    String slug;
    String imageUrl;
    String websiteUrl;

    Set<BadgeDto> badges; //Assuming duplicated badges don't make sense
    List<AwardDto> awards; //But awards can be given multiple times

    List<FirmRegionDto> firmRegions;

}
