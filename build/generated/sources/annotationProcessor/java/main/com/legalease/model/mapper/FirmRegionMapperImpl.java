package com.legalease.model.mapper;

import com.legalease.model.CrossBorderCapability;
import com.legalease.model.response.FirmRegionDto;
import com.legalease.model.response.RegionDto;
import com.legalease.repository.entities.FirmRegionEntity;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-20T10:04:19+0100",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11 (Oracle Corporation)"
)
@Component
public class FirmRegionMapperImpl implements FirmRegionMapper {

    @Autowired
    private RegionMapper regionMapper;

    @Override
    public FirmRegionDto toDto(FirmRegionEntity entity) {
        if ( entity == null ) {
            return null;
        }

        String id = null;
        RegionDto region = null;
        Boolean booking = null;
        CrossBorderCapability crossBorderCapability = null;
        Integer clientSatisfactionRating = null;
        Integer expertiseAndReputationRating = null;

        if ( entity.getId() != null ) {
            id = String.valueOf( entity.getId() );
        }
        region = regionMapper.toDto( entity.getRegion() );
        booking = entity.getBooking();
        crossBorderCapability = entity.getCrossBorderCapability();
        clientSatisfactionRating = entity.getClientSatisfactionRating();
        expertiseAndReputationRating = entity.getExpertiseAndReputationRating();

        FirmRegionDto firmRegionDto = new FirmRegionDto( id, region, booking, crossBorderCapability, clientSatisfactionRating, expertiseAndReputationRating );

        return firmRegionDto;
    }
}
