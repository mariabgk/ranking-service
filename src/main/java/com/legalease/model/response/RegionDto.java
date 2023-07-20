package com.legalease.model.response;

import lombok.Value;

@Value
public class RegionDto {

    Integer id;
    String name;
    String slug;
    RegionGroupDto regionGroup;

}
