package com.legalease.service;

import com.legalease.errorhandling.exception.InvalidRequestException;
import com.legalease.model.mapper.RankingMapper;
import com.legalease.model.response.RankingResponse;
import com.legalease.repository.RankingRepository;
import com.legalease.repository.entities.RankingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RankingService {

    @Autowired
    private RankingRepository rankingRepository;

    @Autowired
    private RankingMapper mapper;

    public RankingResponse getRankingsByRegion(Integer regionId, Integer pageNumber, Integer pageSize){

        validateRegion(regionId);

        Page<RankingEntity> page = rankingRepository.findByFirm_FirmRegions_Region_Id(regionId, PageRequest.of(pageNumber, pageSize));

        return buildResponse(page);

    }

    private void validateRegion(Integer regionId){
        if(isNot170(regionId)){
            throw new InvalidRequestException("Wrong regionId was " + regionId + ", but 170 was expected");
        }
    }

    private boolean isNot170(Integer value){
        return value == null || !value.equals(170);
    }

    private RankingResponse buildResponse(Page<RankingEntity> page){
        return RankingResponse.builder()
                .rankings(page.getContent().stream().map(mapper::toDto).collect(Collectors.toList()))
                .page(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalRecords((int) page.getTotalElements())
                .hasNext(page.hasNext())
                .build();
    }
}
