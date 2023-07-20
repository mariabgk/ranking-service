package com.legalease.model.mapper;

import com.legalease.model.response.FirmDto;
import com.legalease.repository.entities.FirmEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {BadgeMapper.class, AwardMapper.class, FirmRegionMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FirmMapper {

    FirmDto toDto(FirmEntity entity);

}
