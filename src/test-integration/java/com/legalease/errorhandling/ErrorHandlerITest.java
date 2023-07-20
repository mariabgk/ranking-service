package com.legalease.errorhandling;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.legalease.controller.RankingController;
import com.legalease.errorhandling.exception.InvalidRequestException;
import com.legalease.model.mapper.AwardMapperImpl;
import com.legalease.model.mapper.BadgeMapperImpl;
import com.legalease.model.mapper.FirmMapperImpl;
import com.legalease.model.mapper.FirmRegionMapperImpl;
import com.legalease.model.mapper.RankingMapperImpl;
import com.legalease.model.mapper.RegionAreaMapperImpl;
import com.legalease.model.mapper.RegionGroupMapperImpl;
import com.legalease.model.mapper.RegionMapperImpl;
import com.legalease.service.RankingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//TODO if the endpoint is secured, @WebMvcTest needs to be extended to be able to test it in an unsecured way

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RankingController.class)
@ActiveProfiles("test")
class ErrorHandlerITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RankingService rankingService;

    @SpyBean
    private RankingMapperImpl rankingMapper;

    @SpyBean
    private FirmMapperImpl firmMapper;

    @SpyBean
    private BadgeMapperImpl badgeMapper;

    @SpyBean
    private AwardMapperImpl awardMapper;

    @SpyBean
    private FirmRegionMapperImpl firmRegionMapper;

    @SpyBean
    private RegionAreaMapperImpl regionAreaMapper;

    @SpyBean
    private RegionGroupMapperImpl regionGroupMapper;

    @SpyBean
    private RegionMapperImpl regionMapper;

    @Test
    void should_return_badRequest_when_regionId_has_no_value() throws Exception {

        // Given
        int pageNumber = 0;
        int pageSize = 1;
        InvalidRequestException expectedException = new InvalidRequestException("Wrong regionId was null, but 170 was expected");

        given(rankingService.getRankingsByRegion(null, pageNumber, pageSize)).willThrow(expectedException);

        ErrorResponse expected = new ErrorResponse(expectedException.getMessage());

        // When
        mockMvc.perform(get("/ranking?regionId=&pageNumber=0&pageSize=1"))

                //Then
                .andExpect(status().isBadRequest())
                .andExpect(content().json(toJsonString(expected)));
    }

    @Test
    void should_return_badRequest_when_regionId_not_present() throws Exception {

        // Given
        int pageNumber = 0;
        int pageSize = 1;
        InvalidRequestException expectedException = new InvalidRequestException("Wrong regionId was null, but 170 was expected");

        given(rankingService.getRankingsByRegion(null, pageNumber, pageSize)).willThrow(expectedException);

        ErrorResponse expected = new ErrorResponse(expectedException.getMessage());

        // When
        mockMvc.perform(get("/ranking?pageNumber=0&pageSize=1"))

                //Then
                .andExpect(status().isBadRequest())
                .andExpect(content().json(toJsonString(expected)));
    }

    @Test
    void should_return_internalError_when_internal_error() throws Exception {

        // Given
        int regionId = 170;
        int pageNumber = 0;
        int pageSize = 1;
        Exception exception = new RuntimeException("Interval server error");

        given(rankingService.getRankingsByRegion(regionId, pageNumber, pageSize)).willThrow(exception);

        ErrorResponse expected = new ErrorResponse(exception.getMessage());

        // When
        mockMvc.perform(get(getEndpointForParams(regionId, pageNumber, pageSize)))

                //Then
                .andExpect(status().isInternalServerError())
                .andExpect(content().json(toJsonString(expected)));
    }

    @ParameterizedTest(name = "Check bad request when request regionId is \"{0}\"")
    @MethodSource("getInvalidRegionIds")
    void should_return_badRequest_when_invalid_regionId(Integer regionId) throws Exception {

        // Given
        int pageNumber = 0;
        int pageSize = 1;
        InvalidRequestException expectedException = new InvalidRequestException("Wrong regionId was " + regionId + ", but 170 was expected");

        given(rankingService.getRankingsByRegion(regionId, pageNumber, pageSize)).willThrow(expectedException);

        ErrorResponse expected = new ErrorResponse(expectedException.getMessage());

        // When
        mockMvc.perform(get(getEndpointForParams(regionId, pageNumber, pageSize)))

                //Then
                .andExpect(status().isBadRequest())
                .andExpect(content().json(toJsonString(expected)));
    }

    private static Stream<Integer> getInvalidRegionIds(){
        return Stream.of(1, 0, -1);
    }

    private String getEndpointForParams(Integer regionId, Integer pageNumber, Integer pageSize) {
        return String.format("/ranking?regionId=%d&pageNumber=%d&pageSize=%d", regionId, pageNumber, pageSize);
    }

    private String toJsonString(final Object value) throws IOException {
        return objectMapper.writeValueAsString(value);
    }
}
