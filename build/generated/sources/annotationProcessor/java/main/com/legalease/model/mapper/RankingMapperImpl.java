package com.legalease.model.mapper;

import com.legalease.model.Tier;
import com.legalease.model.Trend;
import com.legalease.model.response.FirmDto;
import com.legalease.model.response.RankingDto;
import com.legalease.repository.entities.RankingEntity;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-20T10:04:20+0100",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11 (Oracle Corporation)"
)
@Component
public class RankingMapperImpl implements RankingMapper {

    @Autowired
    private FirmMapper firmMapper;

    @Override
    public RankingDto toDto(RankingEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Integer id = null;
        FirmDto firm = null;
        Trend trend = null;
        Boolean diversity = null;
        Tier tier = null;
        Integer position = null;

        id = entity.getId();
        firm = firmMapper.toDto( entity.getFirm() );
        trend = entity.getTrend();
        diversity = entity.getDiversity();
        tier = entity.getTier();
        position = entity.getPosition();

        RankingDto rankingDto = new RankingDto( id, firm, trend, diversity, tier, position );

        return rankingDto;
    }
}
