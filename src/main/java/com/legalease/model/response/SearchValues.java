package com.legalease.model.response;

import lombok.Value;
import org.springframework.data.domain.PageRequest;

@Value
public class SearchValues {

    Integer regionId;
    PageRequest pageRequest;

}
