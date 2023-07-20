package com.legalease.model.response;

import lombok.Value;

@Value
public class RegionGroupDto {

    Integer id;
    String name;
    String slug;
    RegionAreaDto regionArea;

}
