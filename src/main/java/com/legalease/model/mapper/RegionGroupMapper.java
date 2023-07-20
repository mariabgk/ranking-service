package com.legalease.model.mapper;

import com.legalease.model.response.RegionGroupDto;
import com.legalease.repository.entities.RegionGroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {RegionAreaMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegionGroupMapper {

    RegionGroupDto toDto(RegionGroupEntity entity);
}
