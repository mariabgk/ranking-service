package com.legalease.model.mapper;

import com.legalease.model.response.FirmRegionDto;
import com.legalease.repository.entities.FirmRegionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {RegionMapper.class},  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FirmRegionMapper {

    FirmRegionDto toDto(FirmRegionEntity entity);

}
