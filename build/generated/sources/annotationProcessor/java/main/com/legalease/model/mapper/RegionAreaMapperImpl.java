package com.legalease.model.mapper;

import com.legalease.model.response.RegionAreaDto;
import com.legalease.repository.entities.RegionAreaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-20T10:04:20+0100",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11 (Oracle Corporation)"
)
@Component
public class RegionAreaMapperImpl implements RegionAreaMapper {

    @Override
    public RegionAreaDto toDto(RegionAreaEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Integer id = null;
        String name = null;
        String slug = null;

        id = entity.getId();
        name = entity.getName();
        slug = entity.getSlug();

        RegionAreaDto regionAreaDto = new RegionAreaDto( id, name, slug );

        return regionAreaDto;
    }
}
