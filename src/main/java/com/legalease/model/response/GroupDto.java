package com.legalease.model.response;

import lombok.Value;

@Value
public class GroupDto {

    Integer id;
    String name;
    String slug;
    RegionAreaDto regionArea;

}
