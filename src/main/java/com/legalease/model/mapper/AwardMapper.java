package com.legalease.model.mapper;

import com.legalease.model.response.AwardDto;
import com.legalease.repository.entities.AwardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AwardMapper {

    AwardDto toDto(AwardEntity entity);
}
