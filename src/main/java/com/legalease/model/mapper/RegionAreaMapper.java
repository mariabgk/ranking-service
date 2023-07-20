package com.legalease.model.mapper;

import com.legalease.model.response.RegionAreaDto;
import com.legalease.repository.entities.RegionAreaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegionAreaMapper {

    RegionAreaDto toDto(RegionAreaEntity entity);
}
