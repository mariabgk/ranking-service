package com.legalease.model;

import com.legalease.repository.entities.RankingEntity;
import lombok.Builder;
import lombok.Value;

import java.util.LinkedList;
import java.util.List;

@Value
@Builder
public class RankingResult {

    @Builder.Default
    List<RankingEntity> records = new LinkedList<>();
    @Builder.Default
    Integer totalPages = 0;
    @Builder.Default
    Integer totalRecords = 0;
    @Builder.Default
    Integer page = 0;
    @Builder.Default
    Boolean hasNext = false;
}
