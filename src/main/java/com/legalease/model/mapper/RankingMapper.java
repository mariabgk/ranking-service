package com.legalease.model.mapper;

import com.legalease.model.response.RankingDto;
import com.legalease.repository.entities.RankingEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {FirmMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE, injectionStrategy = InjectionStrategy.FIELD)
public interface RankingMapper {

    RankingDto toDto(RankingEntity entity);

}
