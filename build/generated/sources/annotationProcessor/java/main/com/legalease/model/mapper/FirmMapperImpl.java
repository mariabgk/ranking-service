package com.legalease.model.mapper;

import com.legalease.model.response.AwardDto;
import com.legalease.model.response.BadgeDto;
import com.legalease.model.response.FirmDto;
import com.legalease.model.response.FirmRegionDto;
import com.legalease.repository.entities.AwardEntity;
import com.legalease.repository.entities.BadgeEntity;
import com.legalease.repository.entities.FirmEntity;
import com.legalease.repository.entities.FirmRegionEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-20T10:04:19+0100",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11 (Oracle Corporation)"
)
@Component
public class FirmMapperImpl implements FirmMapper {

    @Autowired
    private BadgeMapper badgeMapper;
    @Autowired
    private AwardMapper awardMapper;
    @Autowired
    private FirmRegionMapper firmRegionMapper;

    @Override
    public FirmDto toDto(FirmEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Set<BadgeDto> badges = null;
        List<AwardDto> awards = null;
        List<FirmRegionDto> firmRegions = null;
        Integer id = null;
        String name = null;
        String slug = null;
        String imageUrl = null;
        String websiteUrl = null;

        badges = badgeEntitySetToBadgeDtoSet( entity.getBadges() );
        awards = awardEntityListToAwardDtoList( entity.getAwards() );
        firmRegions = firmRegionEntityListToFirmRegionDtoList( entity.getFirmRegions() );
        id = entity.getId();
        name = entity.getName();
        slug = entity.getSlug();
        imageUrl = entity.getImageUrl();
        websiteUrl = entity.getWebsiteUrl();

        FirmDto firmDto = new FirmDto( id, name, slug, imageUrl, websiteUrl, badges, awards, firmRegions );

        return firmDto;
    }

    protected Set<BadgeDto> badgeEntitySetToBadgeDtoSet(Set<BadgeEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<BadgeDto> set1 = new HashSet<BadgeDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( BadgeEntity badgeEntity : set ) {
            set1.add( badgeMapper.toDto( badgeEntity ) );
        }

        return set1;
    }

    protected List<AwardDto> awardEntityListToAwardDtoList(List<AwardEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<AwardDto> list1 = new ArrayList<AwardDto>( list.size() );
        for ( AwardEntity awardEntity : list ) {
            list1.add( awardMapper.toDto( awardEntity ) );
        }

        return list1;
    }

    protected List<FirmRegionDto> firmRegionEntityListToFirmRegionDtoList(List<FirmRegionEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<FirmRegionDto> list1 = new ArrayList<FirmRegionDto>( list.size() );
        for ( FirmRegionEntity firmRegionEntity : list ) {
            list1.add( firmRegionMapper.toDto( firmRegionEntity ) );
        }

        return list1;
    }
}
