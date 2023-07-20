package com.legalease.model.response;


import com.legalease.model.Tier;
import com.legalease.model.Trend;
import lombok.Value;

@Value
public class RankingDto {

    Integer id;

    FirmDto firm;

    Trend trend;

    Boolean diversity;

    Tier tier;

    Integer position;
}
