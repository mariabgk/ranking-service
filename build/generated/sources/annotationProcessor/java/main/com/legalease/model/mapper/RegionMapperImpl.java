package com.legalease.model.mapper;

import com.legalease.model.response.RegionDto;
import com.legalease.model.response.RegionGroupDto;
import com.legalease.repository.entities.RegionEntity;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-20T10:04:20+0100",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11 (Oracle Corporation)"
)
@Component
public class RegionMapperImpl implements RegionMapper {

    @Autowired
    private RegionGroupMapper regionGroupMapper;

    @Override
    public RegionDto toDto(RegionEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Integer id = null;
        String name = null;
        String slug = null;
        RegionGroupDto regionGroup = null;

        id = entity.getId();
        name = entity.getName();
        slug = entity.getSlug();
        regionGroup = regionGroupMapper.toDto( entity.getRegionGroup() );

        RegionDto regionDto = new RegionDto( id, name, slug, regionGroup );

        return regionDto;
    }
}
