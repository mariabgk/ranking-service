package com.legalease.model.mapper;

import com.legalease.model.response.BadgeDto;
import com.legalease.repository.entities.BadgeEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-20T10:04:20+0100",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11 (Oracle Corporation)"
)
@Component
public class BadgeMapperImpl implements BadgeMapper {

    @Override
    public BadgeDto toDto(BadgeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BadgeDto badgeDto = new BadgeDto();

        badgeDto.setId( entity.getId() );
        badgeDto.setName( entity.getName() );
        badgeDto.setSlug( entity.getSlug() );

        return badgeDto;
    }
}
