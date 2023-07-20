package com.legalease.model.mapper;

import com.legalease.model.response.BadgeDto;
import com.legalease.repository.entities.BadgeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BadgeMapper {

    BadgeDto toDto(BadgeEntity entity);
}
