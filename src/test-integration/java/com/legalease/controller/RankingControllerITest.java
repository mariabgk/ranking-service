package com.legalease.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.legalease.model.Tier;
import com.legalease.model.Trend;
import com.legalease.model.mapper.AwardMapperImpl;
import com.legalease.model.mapper.BadgeMapperImpl;
import com.legalease.model.mapper.FirmMapperImpl;
import com.legalease.model.mapper.FirmRegionMapperImpl;
import com.legalease.model.mapper.RankingMapperImpl;
import com.legalease.model.mapper.RegionAreaMapperImpl;
import com.legalease.model.mapper.RegionGroupMapperImpl;
import com.legalease.model.mapper.RegionMapperImpl;
import com.legalease.model.response.FirmDto;
import com.legalease.model.response.RankingDto;
import com.legalease.model.response.RankingResponse;
import com.legalease.service.RankingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//TODO if the endpoint is secured, @WebMvcTest needs to be extended to be able to test it in an unsecured way

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RankingController.class)
@ActiveProfiles("test")
class RankingControllerITest {

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
    void should_return_ok_when_valid_regionId() throws Exception {

        // Given
        int regionId = 170;
        int pageNumber = 0;
        int pageSize = 2;
        RankingResponse response = RankingResponse.builder().rankings(createRankings()).page(0).totalPages(1).hasNext(false).build();
        given(rankingService.getRankingsByRegion(regionId, pageNumber, pageSize)).willReturn(response);


        // When
        mockMvc.perform(get(getEndpointForParams(regionId, pageNumber, pageSize)))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().json(toJsonString(response)));
    }

    private List<RankingDto> createRankings(){
        return List.of(
                new RankingDto(1, new FirmDto(1, "Firm", "slug", "imageurl", "website",
                        Set.of(), List.of(), List.of()), Trend.DOWN, true, Tier.T_1, 0),
                new RankingDto(2, new FirmDto(2, "Firm2", "slug2", "imageurl2", "website2",
                        Set.of(), List.of(), List.of()), Trend.STABLE_1, true, Tier.T_2, 0));
    }

    @Test
    void should_return_ok_when_no_rankings() throws Exception {

        // Given
        int regionId = 170;
        int pageNumber = 0;
        int pageSize = 2;
        RankingResponse response = RankingResponse.builder().rankings(List.of()).page(0).totalPages(0).hasNext(false).build();
        given(rankingService.getRankingsByRegion(regionId, pageNumber, pageSize)).willReturn(response);


        // When
        mockMvc.perform(get(getEndpointForParams(regionId, pageNumber, pageSize)))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().json(toJsonString(response)));
    }


    private String getEndpointForParams(Integer regionId, Integer pageNumber, Integer pageSize) {
        return String.format("/rankings?regionId=%d&pageNumber=%d&pageSize=%d", regionId, pageNumber, pageSize);
    }

    private String toJsonString(final Object value) throws IOException {
        return objectMapper.writeValueAsString(value);
    }
}
