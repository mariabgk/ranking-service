package com.legalease.model.response;


import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class RankingResponse {

    List<RankingDto> rankings;
    Integer totalPages;
    Integer totalRecords;
    Integer page;
    Boolean hasNext;
}
