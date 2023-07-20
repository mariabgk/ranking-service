package com.legalease.model.mapper;

import com.legalease.model.response.RegionAreaDto;
import com.legalease.model.response.RegionGroupDto;
import com.legalease.repository.entities.RegionGroupEntity;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-20T10:04:20+0100",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11 (Oracle Corporation)"
)
@Component
public class RegionGroupMapperImpl implements RegionGroupMapper {

    @Autowired
    private RegionAreaMapper regionAreaMapper;

    @Override
    public RegionGroupDto toDto(RegionGroupEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Integer id = null;
        String name = null;
        String slug = null;
        RegionAreaDto regionArea = null;

        id = entity.getId();
        name = entity.getName();
        slug = entity.getSlug();
        regionArea = regionAreaMapper.toDto( entity.getRegionArea() );

        RegionGroupDto regionGroupDto = new RegionGroupDto( id, name, slug, regionArea );

        return regionGroupDto;
    }
}
