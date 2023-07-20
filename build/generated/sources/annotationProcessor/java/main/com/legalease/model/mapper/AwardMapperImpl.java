package com.legalease.model.mapper;

import com.legalease.model.response.AwardDto;
import com.legalease.repository.entities.AwardEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-20T10:04:20+0100",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11 (Oracle Corporation)"
)
@Component
public class AwardMapperImpl implements AwardMapper {

    @Override
    public AwardDto toDto(AwardEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Integer id = null;
        String name = null;

        id = entity.getId();
        name = entity.getName();

        AwardDto awardDto = new AwardDto( id, name );

        return awardDto;
    }
}
