package com.legalease.model.mapper;

import com.legalease.model.response.RegionDto;
import com.legalease.repository.entities.RegionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {RegionGroupMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegionMapper {

    RegionDto toDto(RegionEntity entity);
}
